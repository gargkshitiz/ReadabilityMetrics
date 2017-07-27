package com.org.readability;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ReadabilityApp.class)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ReadabilityAppTests {

	@Autowired
	private MockMvc mvc;

/*	@Autowired
	private TestRestTemplate restTemplate;*/

	@Test
	public void whenGetDemoJsp_thenStatus200() throws Exception {
		mvc.perform(get("/demo/readability").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
				//.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				//.andExpect(jsonPath("$[0].name", is("bob")));
	}
	
	@Test
	public void whenPostComplexWordWithText_thenStatus200() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/learncomplexword/complexWord").accept(MediaType.APPLICATION_JSON_VALUE)
				.content("Hi this is Kshitiz. This text should have one complex word.");
				//.contentType(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder).andExpect(status().isOk());
				//.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				//.andExpect(jsonPath("$[0].name", is("bob")));
	}
	
}
