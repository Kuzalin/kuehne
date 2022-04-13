package com.kuehne.decathlon.utility;

import java.util.HashMap;
import java.util.Map;

import com.kuehne.decathlon.utility.score.FieldEventScoreCalculator;
import com.kuehne.decathlon.utility.score.ScoreCalculator;
import com.kuehne.decathlon.utility.score.TrackEventScoreCalculator;

public final class EventTypeIdentifier {
	
public static Map<String, String> eventObjectMapper = new HashMap<>();
private static final ScoreCalculator fieldEventScoreCalculator = new FieldEventScoreCalculator();
private static final ScoreCalculator trackEventScoreCalculator = new TrackEventScoreCalculator();
	
static {
		if(eventObjectMapper.isEmpty()) {
			eventObjectMapper.put("hundredM","trackEvent");
			eventObjectMapper.put("longJump","fieldEvent");
			eventObjectMapper.put("shotPut","fieldEvent");
			eventObjectMapper.put("highJump","fieldEvent");
			eventObjectMapper.put("fourHunM","trackEvent");
			eventObjectMapper.put("hunTenHurdles","trackEvent");
			eventObjectMapper.put("discusThrow","fieldEvent");
			eventObjectMapper.put("poleVault","fieldEvent");
			eventObjectMapper.put("javelinThrow","fieldEvent");
			eventObjectMapper.put("fifteenHunM","trackEvent");
		}
	}
	
private EventTypeIdentifier()
{
	
}

public static ScoreCalculator getMappedCalculator(String eventKey)
{
	String objectKey = eventObjectMapper.get(eventKey);
	switch(objectKey) {
	case "fieldEvent": return fieldEventScoreCalculator;
	case "trackEvent": return trackEventScoreCalculator;
	default: return null;
	}
}

}
