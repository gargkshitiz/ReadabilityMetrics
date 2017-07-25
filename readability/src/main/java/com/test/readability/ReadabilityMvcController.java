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

	private static final String TEXT = "text";
	
	@Autowired
	private ReadabilityService readabilityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String demoGet() {
        return "readability";
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String demoPost(Model model, @ModelAttribute(TEXT) String text) {
		model.addAttribute("scores", readabilityService.getReadabilityScores(text));
        return "readability";
    }

}