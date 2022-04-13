package com.kuehne.decathlon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.kuehne.decathlon.model.ScoreTable;
import com.kuehne.decathlon.processor.DecathlonScoreProcessor;
import com.kuehne.decathlon.utility.fileparser.CSVFileParser;

@SpringBootTest
public class DecathlonScoreProcessorTest {
	
	@Autowired
	private Environment env;
	
	private static final String fileWriteLocation = "C:\\Users\\naveverma\\Desktop\\Kuenh\\";
	
	private static final String fileReadLocation = "C:\\Users\\naveverma\\Desktop\\Kuenh\\";
	
	private static final String inputFileName = "results.csv";
	
	private static final String outFileName = "ScoreBoard.xml";
	
	private static final String eventNames = "hundredM,longJump,shotPut,highJump,fourHunM,hunTenHurdles,discusThrow,poleVault,javelinThrow,fifteenHunM";
	
	private static final String saperator = ";";
	
	@Test
	public void testCSVParser()
	{
		File file = new File(fileReadLocation+inputFileName);
		CSVFileParser csvParser = new CSVFileParser();
		List<String> recordList=csvParser.readAndParseFile(file);
		assertNotNull(recordList);
		assertEquals(recordList.size(),5);
	}
	
	@Test
	public void testScoring()
	{
		String[] rawEventScoreInput = {"John Smith","12.61","5.00","9.22","1.50","60.39","16.43","21.60","2.60","35.81","5:25.72"};
		String[] eventNameArray = eventNames.split(",");
		DecathlonScoreProcessor dsp = new DecathlonScoreProcessor(env);
		int totalScore = dsp.getScore(rawEventScoreInput, eventNameArray, eventNameArray.length);
		assertEquals(totalScore, 4200);
	}
	
	@Test
	public void testProcessingCompleteScoreAndPosition() throws FileNotFoundException
	{		
		
		DecathlonScoreProcessor dsp = new DecathlonScoreProcessor(env,new CSVFileParser(),eventNames,null,null,saperator);
		List<ScoreTable> scoreTableList = dsp.processScore(new File(fileReadLocation+inputFileName));
		int[] scoreCheck = {4200,4200,3494,3199,3099};
		String[] positionCheck = {"1-2","1-2","3","4","5"};
		assertNotNull(scoreTableList);
		assertEquals(scoreTableList.size(), 5);
		for(int i=0;i<5;i++)
		{
			ScoreTable score = scoreTableList.get(i);
			assertEquals(score.getTotalScore(), scoreCheck[i]);
			assertEquals(score.getPosition(), positionCheck[i]);
		}
	}
	
	@Test
	public void testCompleteProcessingAndWriteFile()
	{
		DecathlonScoreProcessor dsp = new DecathlonScoreProcessor(env,new CSVFileParser(),eventNames,fileWriteLocation,outFileName,saperator);
		String message=dsp.processDecathlonScoreAndWriteFile(new File(fileReadLocation+inputFileName));
		assertEquals(message, "Success");
		
	}

}
