package com.kuehne.decathlon.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PointConvertor {
	
	public static Map<String, String> methodMapper = new HashMap<>();
	
	static {
		if(methodMapper.isEmpty()) {
			methodMapper.put("hundredM","second");
			methodMapper.put("longJump","centiMeter");
			methodMapper.put("shotPut","meter");
			methodMapper.put("highJump","centiMeter");
			methodMapper.put("fourHunM","second");
			methodMapper.put("hunTenHurdles","second");
			methodMapper.put("discusThrow","meter");
			methodMapper.put("poleVault","centiMeter");
			methodMapper.put("javelinThrow","meter");
			methodMapper.put("fifteenHunM","minuteToSecond");
		}
	}
	
	
	public double getMeasuredPoint(String key, String point)
	{
		String convertTo=methodMapper.get(key);		
		switch(convertTo) {		
		case "second": return convertToSecond(point);
		case "minuteToSecond": return convertMinuteToSecond(point);
		case "meter": return convertToMeter(point);
		case "centiMeter": return convertToCentimeter(point);
		default: return 0.0;		
		}
		
	}


	private double convertToCentimeter(String point) {		
		return 100*convertToMeter(point);
	}


	private double convertToMeter(String point) {		
		return Double.parseDouble(point);
	}


	private double convertMinuteToSecond(String point) {
		String[] value = point.split(":");
		double minuteValue = Double.parseDouble(value[0]);
		double secondValue = Double.parseDouble(value[1]);		
		return ((minuteValue*60)+(secondValue));
	}


	private double convertToSecond(String point) {		
		return Double.parseDouble(point);
	}
	
	

}
