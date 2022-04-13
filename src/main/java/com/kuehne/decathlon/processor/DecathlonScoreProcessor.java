package com.kuehne.decathlon.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.kuehne.decathlon.model.ScoreTable;
import com.kuehne.decathlon.utility.AppUtility;
import com.kuehne.decathlon.utility.EventTypeIdentifier;
import com.kuehne.decathlon.utility.fileparser.CSVFileParser;
import com.kuehne.decathlon.utility.score.ScoreCalculator;

@Service
public class DecathlonScoreProcessor {	
	
	private static final Logger logger = LogManager.getLogger(DecathlonScoreProcessor.class);	
	
	private static final String FILE_FORMAT_EXCEPTION = "Incorrect File to process";
	
	@Autowired
	private CSVFileParser csvFileParser;
	
	@Autowired
	private Environment env;	
	
	
	@Value(value = "${decathlon.event.names}")
	private String allEventsName;
	
	@Value(value="${writeLocation}")
	private String writeLocation;
	
	@Value(value="${outputFileName}")
	private String fileName;	
	
	@Value(";")
	private String saperator;	
	
	public DecathlonScoreProcessor() {
	}
	
	public DecathlonScoreProcessor(Environment env,CSVFileParser csvFileParser, String allEventsName, String writeLocation, String fileName,String saperator) {
		this.env=env;
		this.csvFileParser=csvFileParser;
		this.allEventsName=allEventsName;
		this.writeLocation=writeLocation;
		this.fileName=fileName;
		this.saperator=saperator;
	}
	
	public DecathlonScoreProcessor(Environment env) {
		this.env=env;
	}
	
	public String processDecathlonScoreAndWriteFile(File file)
	{	
		String returnMessage = "Success";
		try {	
		List<ScoreTable> scoreTable=processScore(file);
		AppUtility.writeXMLFile(AppUtility.convertObjectToXMLString(scoreTable).toString(),writeLocation+fileName);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			logger.error(e.getMessage(), e);
			returnMessage = "ERR_010";
		} catch (JAXBException e) {
			logger.error(e.getMessage(), e);
			returnMessage = "ERR_020";
		}
		catch(Exception e)
		{
		    logger.error(e.getMessage(), e);
		    returnMessage = "ERR_030";
		}		
		return returnMessage;		
	}
	
	
	public List<ScoreTable> processScore(File file) throws FileNotFoundException
	{
		if(!AppUtility.verifyCSVFile(file))
			throw new FileNotFoundException(FILE_FORMAT_EXCEPTION);
		List<String> fileRecords=csvFileParser.readAndParseFile(file);		
		String[] eventNames = allEventsName.split(",");
		int length = eventNames.length;
		List<ScoreTable> scoreTableList = new ArrayList<>();
		fileRecords.forEach(record ->{
			String[] recordFieldValue = record.split(saperator);		
			scoreTableList.add(setRecordData(recordFieldValue,getScore(recordFieldValue,eventNames,length)));
		});
		
		AppUtility.sortOnScore(scoreTableList);	
		AppUtility.setPosition(scoreTableList);
		
		return scoreTableList;
	}

	private ScoreTable setRecordData(String[] recordFieldValue, int totalScore) {
			ScoreTable scoreTable = new ScoreTable();
			scoreTable.setName(recordFieldValue[0]);
			scoreTable.setHundredM(recordFieldValue[1]);
			scoreTable.setLongJump(recordFieldValue[2]);
			scoreTable.setShortPut(recordFieldValue[3]);
			scoreTable.setHighJump(recordFieldValue[4]);
			scoreTable.setFourHunM(recordFieldValue[5]);
			scoreTable.setHunTenHurdles(recordFieldValue[6]);
			scoreTable.setDiscussThrow(recordFieldValue[7]);
			scoreTable.setPoleVault(recordFieldValue[8]);
			scoreTable.setJavelinThrow(recordFieldValue[9]);
			scoreTable.setFifteenHunM(recordFieldValue[10]);
			scoreTable.setTotalScore(totalScore);
			return scoreTable;
	}
	
	public int getScore(String[] recordFieldValue,String[] eventNames, int eventLenght)
	{		
		int totalScore = 0;
		for(int i=0;i<eventLenght;i++) {
			String eventKey = eventNames[i];
			String gameMatrix = env.getProperty(eventKey);
			String point = recordFieldValue[i+1];
			ScoreCalculator sc = EventTypeIdentifier.getMappedCalculator(eventKey);
			totalScore+=sc.score(point, eventKey,gameMatrix);
		}	
		return totalScore;
	}	
	
	
	
	
	

}
