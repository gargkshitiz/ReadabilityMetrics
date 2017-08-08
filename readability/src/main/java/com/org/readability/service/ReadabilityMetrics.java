package com.org.readability.service;

import java.util.List;
/**
 * Json output for accessing metrics
 * 
 * @author Kshitiz Garg
 */
public class ReadabilityMetrics {
	
	private double gunningFogIndex;
	private String gfiInterpretationText;
	private double fleschReadingEase;
	private String freInterpretationText;
	private int syllables;
	private int words;
	private int sentences;
	private int complexWordCount;
	private double averageWordsPerSentence;
	
	private List<String> complexWords;
	
	public double getGunningFogIndex() {
		return gunningFogIndex;
	}
	public void setGunningFogIndex(double gunningFogIndex) {
		this.gunningFogIndex = gunningFogIndex;
	}
	public double getFleschReadingEase() {
		return fleschReadingEase;
	}
	public void setFleschReadingEase(double fleschReadingEase) {
		this.fleschReadingEase = fleschReadingEase;
	}
	public int getSyllables() {
		return syllables;
	}
	public void setSyllables(int syllables) {
		this.syllables = syllables;
	}
	public int getWords() {
		return words;
	}
	public void setWords(int words) {
		this.words = words;
	}
	public int getSentences() {
		return sentences;
	}
	public void setSentences(int sentences) {
		this.sentences = sentences;
	}
	public int getComplexWordCount() {
		return complexWordCount;
	}
	public void setComplexWordCount(int complexWordCount) {
		this.complexWordCount = complexWordCount;
	}
	public List<String> getComplexWords() {
		return complexWords;
	}
	public void setComplexWords(List<String> complexWords) {
		this.complexWords = complexWords;
	}
	public double getAverageWordsPerSentence() {
		return averageWordsPerSentence;
	}
	public void setAverageWordsPerSentence(double averageWordsPerSentence) {
		this.averageWordsPerSentence = averageWordsPerSentence;
	}
	public String getFreInterpretationText() {
		return freInterpretationText;
	}
	public void setFreInterpretationText(String freInterpretationText) {
		this.freInterpretationText = freInterpretationText;
	}
	public String getGfiInterpretationText() {
		return gfiInterpretationText;
	}
	public void setGfiInterpretationText(String gfiInterpretationText) {
		this.gfiInterpretationText = gfiInterpretationText;
	}
	@Override
	public String toString() {
		return "ReadabilityMetrics [gunningFogIndex=" + gunningFogIndex + ", gfiInterpretationText="
				+ gfiInterpretationText + ", fleschReadingEase=" + fleschReadingEase + ", freInterpretationText="
				+ freInterpretationText + ", syllables=" + syllables + ", words=" + words + ", sentences=" + sentences
				+ ", complexWordCount=" + complexWordCount + ", averageWordsPerSentence=" + averageWordsPerSentence
				+ "]";
	}
	
}