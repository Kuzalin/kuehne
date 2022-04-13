package com.kuehne.decathlon.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;




@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ScoreTable")
public class ScoreTable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@XmlAttribute(name = "position")
	private String position="1";
	
	@XmlAttribute(name = "total-score")
	private int totalScore;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlElement(name = "100-M")
	private String hundredM;
	
	@XmlElement(name = "Long-Jump")
	private String longJump;
	
	@XmlElement(name = "Short-Put")
	private String shortPut;
	
	@XmlElement(name = "High-Jump")
	private String highJump;
	
	@XmlElement(name = "400-M")
	private String fourHunM;
	@XmlElement(name = "110-M-Hurdles")
	private String hunTenHurdles;
	
	@XmlElement(name = "Discuss-Throw")
	private String discussThrow;
	
	@XmlElement(name = "Pole-valut")
	private String poleVault;
	
	@XmlElement(name = "Jevelin-Throw")
	private String javelinThrow;
	
	@XmlElement(name = "1500-M")
	private String fifteenHunM;

	
	
	
	
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHundredM() {
		return hundredM;
	}
	public void setHundredM(String hundredM) {
		this.hundredM = hundredM;
	}
	public String getFourHunM() {
		return fourHunM;
	}
	public void setFourHunM(String fourHunM) {
		this.fourHunM = fourHunM;
	}
	public String getHunTenHurdles() {
		return hunTenHurdles;
	}
	public void setHunTenHurdles(String hunTenHurdles) {
		this.hunTenHurdles = hunTenHurdles;
	}
	public String getFifteenHunM() {
		return fifteenHunM;
	}
	public void setFifteenHunM(String fifteenHunM) {
		this.fifteenHunM = fifteenHunM;
	}	
	
	public String getLongJump() {
		return longJump;
	}
	public void setLongJump(String longJump) {
		this.longJump = longJump;
	}
	public String getShortPut() {
		return shortPut;
	}
	public void setShortPut(String shortPut) {
		this.shortPut = shortPut;
	}
	public String getHighJump() {
		return highJump;
	}
	public void setHighJump(String highJump) {
		this.highJump = highJump;
	}
	public String getDiscussThrow() {
		return discussThrow;
	}
	public void setDiscussThrow(String discussThrow) {
		this.discussThrow = discussThrow;
	}
	public String getPoleVault() {
		return poleVault;
	}
	public void setPoleVault(String poleVault) {
		this.poleVault = poleVault;
	}
	public String getJavelinThrow() {
		return javelinThrow;
	}
	public void setJavelinThrow(String javelinThrow) {
		this.javelinThrow = javelinThrow;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
