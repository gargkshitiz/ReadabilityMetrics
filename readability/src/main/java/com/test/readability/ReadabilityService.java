package com.test.readability;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.aliasi.sentences.IndoEuropeanSentenceModel;
import com.aliasi.sentences.SentenceModel;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;

/**
 * Implements various readability indexes
 * 
 * @author Kshitiz Garg
 * Inspired from https://github.com/ipeirotis/ReadabilityMetrics
 * 
 */
@Service
public class ReadabilityService {

	/**
	 * This list is just to demo the machine learning aspect. Needs to be maintained somewhere in DB/Cache
	 */
	private final Set<String> complexWordList = new HashSet<>();
	
	private static final Pattern VOWELS = Pattern.compile("[^aeiouy]+");

	private static final String[] staticSubMatches = { "cial", "tia", "cius", "cious", "giu", "ion", "iou" };
	private static final Pattern[] regexSubMatches = { Pattern.compile(".*sia$"), Pattern.compile(".*.ely$"), Pattern.compile(".*[^td]ed$") };

	private static final String[] staticAddMatches = { "ia", "riet", "dien", "iu", "io", "ii", "microor" };
	private static final Pattern[] regexAddMatches = { Pattern.compile(".*[aeiouym]bl$"),
			Pattern.compile(".*[aeiou]{3}.*"), Pattern.compile("^mc.*"), Pattern.compile(".*ism$"),
			Pattern.compile(".*isms$"), Pattern.compile(".*([^aeiouy])\\1l$"), Pattern.compile(".*[^l]lien.*"),
			Pattern.compile("^coa[dglx]..*"), Pattern.compile(".*[^gq]ua[^auieo].*"), Pattern.compile(".*dnt$") };
	
    private ReadabilityScores getNumberOfWordsSyllablesAndComplexWords(String text) {
		String cleanText = getCleanText(text);
		String[] word = cleanText.split(" ");
		int words = 0;
		int syllables = 0;
		int complex = 0;
		List<String> complexWords = new LinkedList<>();
		for (String w : word) {
			int syllableCount = 0;
			if (w.length() > 0) {
				syllableCount = getSyllablesCount(w);
				if (syllableCount>2 && !complexWordList.contains(w)) {
					complexWords.add(w);
					complex++;
				}
				words++;
				syllables = syllables + syllableCount;
			}
		}
		ReadabilityScores readabilityScores = new ReadabilityScores();
		readabilityScores.setComplexWordCount(complex);
		readabilityScores.setComplexWords(complexWords);
		readabilityScores.setSyllables(syllables);
		readabilityScores.setWords(words);
		return readabilityScores;
	}
    
    private int getNumberOfSentences(String text) {
		int l = getSentences(text).length;
		if (l > 0)
			return l;
		else if (text.length() > 0)
			return 1;
		else
			return 0;
	}
    
    /**
     * 
     * http://en.wikipedia.org/wiki/Gunning-Fog_Index
     * 
     * @param text
     * @return the Gunning-Fog Index for text
     */
    private double getGunningFogScore(int numberOfWords, int numberOfSentences, int numberOfComplexWords ) {
        double score = 0.4 * (numberOfWords/numberOfSentences + 100 * numberOfComplexWords/numberOfWords);
        return round(score, 3);
    }
    
    /**
	 * 
	 * http://en.wikipedia.org/wiki/Flesch-Kincaid_Readability_Test
     * @param numberOfSentences 
     * @param numberOfSyllables 
	 * 
	 * @param text
	 * @return Returns the Flesch_Reading Ease value for the text
	 */
    private double getFleschReadingEase(int numberOfWords, int numberOfSentences, int numberOfSyllables ) {
		double score = 206.835 - 1.015 * numberOfWords / numberOfSentences - 84.6 * numberOfSyllables/numberOfWords;
		return round(score, 3);
	}

    private double round(double d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

    private String getCleanText(String line) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c < 128 && Character.isLetter(c)) {
				builder.append(c);
			} else {
				builder.append(' ');
			}
		}
		return builder.toString().toLowerCase();
	}
    
    private int getSyllablesCount(String word) {

		word = word.toLowerCase();
		if (word.equals("w")) {
			return 2;
		}
		if (word.length() == 1) {
			return 1;
		}
		word = word.replaceAll("'", " ");

		if (word.endsWith("e")) {
			word = word.substring(0, word.length() - 1);
		}

		String[] phonems = VOWELS.split(word);

		int syl = 0;
		for (int i = 0; i < staticSubMatches.length; i++) {
			if (word.contains(staticSubMatches[i])) {
				syl -= 1;
			}
		}
		for (int i = 0; i < regexSubMatches.length; i++) {
			if (regexSubMatches[i].matcher(word).matches()) {
				syl -= 1;
			}
		}
		for (int i = 0; i < staticAddMatches.length; i++) {
			if (word.contains(staticAddMatches[i])) {
				syl += 1;
			}
		}
		for (int i = 0; i < regexAddMatches.length; i++) {
			if (regexAddMatches[i].matcher(word).matches()) {
				syl += 1;
			}
		}

		for (int i = 0; i < phonems.length; i++) {
			if (phonems[i].length() > 0) {
				syl++;
			}
		}

		if (syl == 0) {
			syl = 1;
		}

		return syl;
	}
    
	private String[] getSentences(String text) {
		TokenizerFactory TOKENIZER_FACTORY = new IndoEuropeanTokenizerFactory();
		SentenceModel SENTENCE_MODEL = new IndoEuropeanSentenceModel();
		
		ArrayList<String> tokenList = new ArrayList<>();
		ArrayList<String> whiteList = new ArrayList<>();
		Tokenizer tokenizer = TOKENIZER_FACTORY.tokenizer(text.toCharArray(), 0, text.length());
		tokenizer.tokenize(tokenList, whiteList);

		String[] tokens = new String[tokenList.size()];
		String[] whites = new String[whiteList.size()];
		tokenList.toArray(tokens);
		whiteList.toArray(whites);
		int[] sentenceBoundaries = SENTENCE_MODEL.boundaryIndices(tokens, whites);

		if (sentenceBoundaries.length < 1) {
			return new String[0];
		}

		String[] result = new String[sentenceBoundaries.length];

		int sentStartTok = 0;
		int sentEndTok = 0;
		for (int i = 0; i < sentenceBoundaries.length; ++i) {
			sentEndTok = sentenceBoundaries[i];
			StringBuilder sb = new StringBuilder();
			for (int j = sentStartTok; j <= sentEndTok; j++) {
				sb.append(tokens[j] + whites[j + 1]);
			}
			result[i] = sb.toString();
			sentStartTok = sentEndTok + 1;
		}
		return result;
	}
    
	public ReadabilityScores getReadabilityScores(String text) {
		
		ReadabilityScores readabilityScores = getNumberOfWordsSyllablesAndComplexWords(text);
		int numberOfSentences = getNumberOfSentences(text);
		readabilityScores.setSentences(numberOfSentences);
		readabilityScores.setAverageWordsPerSentence(readabilityScores.getWords()/numberOfSentences);
		double fleschReadingEase = getFleschReadingEase(readabilityScores.getWords(),numberOfSentences, readabilityScores.getSyllables());
		double gunningFogScore = getGunningFogScore(readabilityScores.getWords(), numberOfSentences, readabilityScores.getComplexWordCount());
		
		readabilityScores.setFleschReadingEase(fleschReadingEase);
		readabilityScores.setGunningFogScore(gunningFogScore);
		return readabilityScores;
	}

	public void learnComplexWord(String complexWord) {
		complexWordList.add(complexWord);
	}

}