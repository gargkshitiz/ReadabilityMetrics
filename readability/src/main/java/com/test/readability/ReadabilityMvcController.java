package com.test.readability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Kshitiz Garg
 */
@Controller
@RequestMapping(value = {"/demo/readability"})
public class ReadabilityMvcController {

	private static final String API_INPUT = "apiInput";

	private static final String READABILITY = "readability";

	@Autowired
	private ReadabilityService readabilityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String demoGet(Model model) {
		model.addAttribute(API_INPUT, new ApiInput());
        return READABILITY;
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String demoPost(Model model, @ModelAttribute(API_INPUT) ApiInput apiInput) {
		model.addAttribute("scores", readabilityService.getReadabilityScores(apiInput.getText()));
		model.addAttribute(API_INPUT, apiInput);
        return READABILITY;
    }
	
}