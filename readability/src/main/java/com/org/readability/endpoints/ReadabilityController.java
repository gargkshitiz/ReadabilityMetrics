package com.org.readability.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.readability.swagger.ApiDocumentation;
import com.org.readability.entity.ApiInput;
import com.org.readability.service.ReadabilityMetrics;
import com.org.readability.service.ReadabilityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * A RESTFul controller for learning a complex word and get back readability scores
 * 
 * @author Kshitiz Garg
 */
@Api(value = ApiDocumentation.LEARN_WORD)
@RestController
@RequestMapping(ApiDocumentation.LEARN_WORD_API)
public class ReadabilityController {

	@Autowired
	private ReadabilityService readabilityService;
	
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Complex word learnt successfully"),
        @ApiResponse(code = 400, message = "Bad request")
	})
	@ApiOperation(value =  ApiDocumentation.LEARN_WORD_POST , httpMethod = ApiDocumentation.POST)
	@RequestMapping(path="/{word}", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ReadabilityMetrics learnComplexWord(@PathVariable String word, @RequestBody ApiInput apiInput) {
		readabilityService.learnComplexWord(word);
		ReadabilityMetrics readabilityMetrics = readabilityService.getReadabilityMetrics(apiInput.getText());
		return readabilityMetrics;
	}


}