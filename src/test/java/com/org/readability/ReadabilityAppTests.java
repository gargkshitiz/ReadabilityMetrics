package com.org.readability;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.readability.entity.ApiInput;
import com.org.readability.ReadabilityApp;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ReadabilityApp.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ReadabilityAppTests {

	private static final String TEXT_VALUE = "Hi this is Kshitiz. This awesome text should have one complex word.";
	@Autowired
	private MockMvc mvc;

	//@Autowired
	//private TestRestTemplate restTemplate;

	@Test
	public void whenGetDemoJsp_thenStatus200() throws Exception {
		mvc.perform(get("/demo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void whenPostComplexWordWithText_thenStatus200() throws Exception {
		ApiInput apiInput = new ApiInput();
		apiInput.setText(TEXT_VALUE);
		String json = new ObjectMapper().writeValueAsString(apiInput);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/learncomplexword/complexWord").content(json)
				.contentType(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("gunningFogScore", is(5.6)))
				.andExpect(jsonPath("fleschReadingEase", is(87.945)))
				.andExpect(jsonPath("syllables", is(16)))
				.andExpect(jsonPath("words", is(12)))
				.andExpect(jsonPath("sentences", is(2)))
				.andExpect(jsonPath("complexWordCount", is(1)))
				.andExpect(jsonPath("averageWordsPerSentence", is(6.0)))
				.andExpect(jsonPath("complexWords", is(Arrays.asList("awesome"))));
	}
	
}