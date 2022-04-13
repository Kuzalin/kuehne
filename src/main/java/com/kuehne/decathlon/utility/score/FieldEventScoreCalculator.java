package com.kuehne.decathlon.utility.score;

import org.springframework.stereotype.Component;

@Component
public class FieldEventScoreCalculator implements ScoreCalculator{	

	@Override
	public int gameScore(double point, String gameMatrix) {		
		String[] matrixValue = gameMatrix.split(",");		
		double a = Double.parseDouble(matrixValue[0]);
		double b = Double.parseDouble(matrixValue[1]);
		double c = Double.parseDouble(matrixValue[2]);		
		int gameScore = (int) (a*Math.pow((point-b),c)); 		
		return gameScore;
	}	
	
}
