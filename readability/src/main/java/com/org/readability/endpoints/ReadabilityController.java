package com.org.readability.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.readability.entity.ApiInput;
import com.org.readability.service.ReadabilityMetrics;
import com.org.readability.service.ReadabilityService;

/**
 * A RESTFul controller for learning a complex word and get back readability scores
 * 
 * @author Kshitiz Garg
 */
@RestController
@RequestMapping()
public class ReadabilityController {

	@Autowired
	private ReadabilityService readabilityService;
	
	@RequestMapping(path="/learncomplexword/{word}", method = RequestMethod.POST)
	public ReadabilityMetrics learnComplexWord(@PathVariable String word, @RequestBody ApiInput apiInput) {
		readabilityService.learnComplexWord(word);
		return readabilityService.getReadabilityMetrics(apiInput.getText());
	}


}