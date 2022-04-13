package com.kuehne.decathlon.utility.score;

import com.kuehne.decathlon.utility.PointConvertor;

@FunctionalInterface
public interface ScoreCalculator {	
	
	public static final PointConvertor pointConvertor = new PointConvertor();
	

	public int gameScore(double points, String gameMatrix);
	
	public default int score(String points, String eventKey,String gameMatrix)
	{
		double point = pointConvertor.getMeasuredPoint(eventKey, points);		
		return this.gameScore(point, gameMatrix);
	}
	
	
}
