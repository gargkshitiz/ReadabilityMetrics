package com.test.readability;

import java.util.List;

public class ReadabilityScores {
	
	private double gunningFogScore;
	private double fleschReadingEase;
	private int syllables;
	private int words;
	private int sentences;
	private int complexWordCount;
	private double averageWordsPerSentence;
	
	private List<String> complexWords;
	
	public double getGunningFogScore() {
		return gunningFogScore;
	}
	public void setGunningFogScore(double gunningFogScore) {
		this.gunningFogScore = gunningFogScore;
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
	@Override
	public String toString() {
		return "gunningFogScore=" + gunningFogScore + "\nfleschReadingEase=" + fleschReadingEase
				+ "\nsyllables=" + syllables + "\nwords=" + words + "\nsentences=" + sentences + "\ncomplexWordCount="
				+ complexWordCount + "\naverageWordsPerSentence=" + averageWordsPerSentence;
	}
	
}