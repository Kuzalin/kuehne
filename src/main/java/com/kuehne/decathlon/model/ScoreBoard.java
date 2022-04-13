package com.kuehne.decathlon.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ScoreBoard")
public class ScoreBoard {	
	
	@XmlElement(name="Score")
	private List<ScoreTable> scoreTableList;

	public List<ScoreTable> getScoreTableList() {
		return scoreTableList;
	}

	public void setScoreTableList(List<ScoreTable> scoreTableList) {
		this.scoreTableList = scoreTableList;
	}

}
