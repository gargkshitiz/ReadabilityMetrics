package com.test.readability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * A RESTFul controller for accessing readability scores
 * 
 * @author Kshitiz Garg
 */
@RestController
@RequestMapping()
public class ReadabilityController {

	@Autowired
	private ReadabilityService readabilityService;
	
	@RequestMapping(path="/learncomplexword/{word}", method = RequestMethod.POST)
	public ReadabilityScores learnComplexWord(@PathVariable String word, @RequestBody ApiInput apiInput) {
		readabilityService.learnComplexWord(word);
		return readabilityService.getReadabilityScores(apiInput.getText());
	}


}