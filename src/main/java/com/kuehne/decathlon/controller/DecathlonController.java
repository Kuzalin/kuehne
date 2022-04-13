package com.kuehne.decathlon.controller;

import java.io.File;
import java.io.IOException;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kuehne.decathlon.processor.DecathlonScoreProcessor;
import com.kuehne.decathlon.utility.AppUtility;

@RestController
public class DecathlonController {
	
	private static final Logger log = LogManager.getLogger(DecathlonController.class);
	
	@Autowired
	private DecathlonScoreProcessor decathScoreProcessor;
	
	@PostMapping(value="/scoreCalculator",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> calculateScore(@RequestParam("file") MultipartFile uploadedFile)
	{
		try {
			File file=AppUtility.convert(uploadedFile);
			decathScoreProcessor.processDecathlonScoreAndWriteFile(file);
		} catch (IOException e) {			
			log.error("Failed", e);
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
