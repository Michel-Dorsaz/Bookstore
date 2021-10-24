package com.example.Bookstore;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;



	@Test
	public void testIndexPageRedirectionToLogin() throws Exception {

		this.mockMvc.perform(get("/index")).andDo(print())
			.andExpect(status().isMovedTemporarily());

	}
	
	@Test
	public void testLoginPageResponse() throws Exception {


		this.mockMvc.perform(get("/login")).andDo(print())
			.andExpect(status().isOk());

	}
	
	@Test
	public void testbooklistPageRedirectionToLogin() throws Exception {


		this.mockMvc.perform(get("/booklist")).andDo(print())
		.andExpect(status().isMovedTemporarily());

	}
}
