package com.example.anagram;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AnagramApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testNormal() throws Exception {
		String a = "bad credit";
		String b = "debit card";
		this.mvc.perform(get("/api/anagram/"+a+"&"+b)).andExpect(status().isOk())
				.andExpect(content().string("true"));
	}

	@Test
	public void testReverse() throws Exception {
		String a = "bad credit";
		String b = "debit card";
		this.mvc.perform(get("/api/anagram/"+b+"&"+a)).andExpect(status().isOk())
				.andExpect(content().string("true"));
	}

	@Test
	public void testExtraSpace() throws Exception {
		String a = "bad  credit";
		String b = "debit card";
		this.mvc.perform(get("/api/anagram/"+a+"&"+b)).andExpect(status().isOk())
				.andExpect(content().string("true"));
	}

	@Test
	public void testOneSpace() throws Exception {
		String a = "badcredit";
		String b = "debit card";

		this.mvc.perform(get("/api/anagram/"+a+"&"+b)).andExpect(status().isOk())
				.andExpect(content().string("true"));
	}

	@Test
	public void testNormalFail() throws Exception {
		String a = "no credit";
		String b = "debit card";
		this.mvc.perform(get("/api/anagram/"+a+"&"+b)).andExpect(status().isOk())
				.andExpect(content().string("false"));
	}

	@Test
	public void testDifferentSize() throws Exception {
		String a = "badd credit";
		String b = "debit card";
		this.mvc.perform(get("/api/anagram/"+a+"&"+b)).andExpect(status().isOk())
				.andExpect(content().string("false"));
	}

	@Test
	public void testDifferentSizeReverse() throws Exception {
		String a = "bad credit";
		String b = "debitt card";
		this.mvc.perform(get("/api/anagram/"+a+"&"+b)).andExpect(status().isOk())
				.andExpect(content().string("false"));
	}

}
