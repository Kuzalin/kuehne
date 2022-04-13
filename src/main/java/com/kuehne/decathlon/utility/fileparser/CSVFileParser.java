package com.kuehne.decathlon.utility.fileparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CSVFileParser{
	
	private static final Logger logger = LogManager.getLogger(CSVFileParser.class);

	FileParser<String> fp = (f,l) -> {
	   try {
		BufferedReader br = new BufferedReader(new FileReader(f));
		String record = br.readLine();
		while(record!=null && !record.isEmpty())
		{			
			l.add(record);
			record = br.readLine();
		}
	} catch (IOException e) {
		logger.error("Error Occured While Parsing CSV file", e);
	} 	
	};	
	
	public List<String> readAndParseFile(File file)
	{
		List<String> inputBeanList = new ArrayList<>();
		fp.parser(file, inputBeanList);
		return inputBeanList;
	}

}
