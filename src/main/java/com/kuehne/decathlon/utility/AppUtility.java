package com.kuehne.decathlon.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.kuehne.decathlon.model.ScoreBoard;
import com.kuehne.decathlon.model.ScoreTable;

public final class AppUtility {
	

	
private AppUtility()
{
}

public static void sortOnScore(List<ScoreTable> scoreTableList)
	{
		scoreTableList.sort(new Comparator<ScoreTable>() {

			public int compare(ScoreTable scoreRecord1, ScoreTable scoreRecord2) {
				int score1 = scoreRecord1.getTotalScore();
				int score2 = scoreRecord2.getTotalScore();
				
				if(score1>score2)
					return -1;
				else if(score1<score2)
					return 1;
				else
					return 0;	
				
			}
		});
	}

public static void setPosition(List<ScoreTable> scoreTableList)
{
	int len = scoreTableList.size();
	int i=0;
	
	while(i<len)
	{
	
		int totalScore = scoreTableList.get(i).getTotalScore();
		int sameScoreCount = 0;
		
		for(int j=i+1;j<len;j++)
		{
			
			int totalScoreNext = scoreTableList.get(j).getTotalScore();
			if(totalScore!=totalScoreNext)
				break;
			
			sameScoreCount++;
				
		}
		
		String position = (i+1)+(sameScoreCount>0?"-"+String.valueOf(i+sameScoreCount+1):"");
		int nextPosition = i;
		
		do
		{	
			ScoreTable scoreRecord = scoreTableList.get(i);
			scoreRecord.setPosition(position);			
			i++;
		}while(i<=nextPosition+sameScoreCount);
	}
}


public static StringWriter convertObjectToXMLString(List<ScoreTable> scoreTable) throws JAXBException {
	ScoreBoard scoreBoard = new ScoreBoard();
	scoreBoard.setScoreTableList(scoreTable);
	StringWriter sw = new StringWriter();
	JAXBContext jaxBContext = JAXBContext.newInstance(ScoreBoard.class);
	Marshaller marshaller = jaxBContext.createMarshaller();
	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
	marshaller.marshal(scoreBoard,sw);
	return sw;
}

public static void writeXMLFile(String xmlString,String writeFileLocation) throws ParserConfigurationException, SAXException, IOException, TransformerException
{	
	BufferedWriter writer = new BufferedWriter(new FileWriter(new File(writeFileLocation)));
	writer.write(xmlString);
	writer.close();		
}

public static boolean verifyCSVFile(File file)
{
	String fileExtention=file.getName().split("\\.")[1];
	return fileExtention.equalsIgnoreCase("csv");
}

public static File convert(MultipartFile file) throws IOException {
	File convFile = new File(file.getOriginalFilename());
	convFile.createNewFile();
	FileOutputStream fos = new FileOutputStream(convFile);
	fos.write(file.getBytes());
	fos.close();
	return convFile;
}
	
}
