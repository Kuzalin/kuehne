package com.kuehne.decathlon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kuehne.decathlon.model.ScoreTable;
import com.kuehne.decathlon.processor.DecathlonScoreProcessor;

@SpringBootApplication
public class DecathlonApplication {

	@Autowired
	private DecathlonScoreProcessor dsp;
	
	public static void main(String[] args) {
		SpringApplication.run(DecathlonApplication.class, args);
		//new DecathlonApplication().getApplication();
	}

	/*
	 * public void getApplication() { String fileLocation =
	 * "C:\\Users\\naveverma\\Desktop\\Kuenh\\results.csv";
	 * //DecathlonScoreProcessor dsp = new DecathlonScoreProcessor();
	 * List<ScoreTable> scoreTableList=dsp.processScore(fileLocation);
	 * System.out.println(scoreTableList); }
	 */
	
	
}
