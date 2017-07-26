package com.test.readability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(value="/api/readability", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ReadabilityScores getScores(@RequestBody String text) {
		return readabilityService.getReadabilityScores(text);
	}
	
	@RequestMapping(path="/learncomplexword/{word}", method = RequestMethod.POST)
	public void learnComplexWord(@PathVariable String word) {
		readabilityService.learnComplexWord(word);
	}


}