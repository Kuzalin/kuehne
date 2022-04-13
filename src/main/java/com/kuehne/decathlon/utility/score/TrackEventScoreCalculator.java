package com.kuehne.decathlon.utility.score;

import org.springframework.stereotype.Component;

@Component
public class TrackEventScoreCalculator implements ScoreCalculator {	

	@Override
	public int gameScore(double point, String gameMatrix) {
		String[] matrixValue = gameMatrix.split(",");		
		double a = Double.parseDouble(matrixValue[0]);
		double b = Double.parseDouble(matrixValue[1]);
		double c = Double.parseDouble(matrixValue[2]);		
		int gameScore = (int) (a*Math.pow((b-point),c)); 		
		return gameScore;
	}
	
}
