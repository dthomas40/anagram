package com.example.anagram;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AnagramListTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testNormal() throws Exception {

        List<String> list = new ArrayList<String>();
        list.add("bad credit");
        list.add("debit card");
        list.add("cinema");
        list.add("anemic");
        list.add("ice man");

        String words = list.stream()
                .collect(Collectors.joining(","));

        System.out.println(words);


        this.mvc.perform(get("/api/anagram/list?words="+words)).andExpect(status().isOk())
                .andExpect(content().string("[[\"bad credit\",\"debit card\"],[\"cinema\",\"anemic\",\"ice man\"]]"));
    }

    @Test
    public void testReordered() throws Exception {

        List<String> list = new ArrayList<String>();
        list.add("bad credit");
        list.add("cinema");
        list.add("anemic");
        list.add("debit card");
        list.add("ice man");

        String words = list.stream()
                .collect(Collectors.joining(","));

        System.out.println(words);


        this.mvc.perform(get("/api/anagram/list?words="+words)).andExpect(status().isOk())
                .andExpect(content().string("[[\"bad credit\",\"debit card\"],[\"cinema\",\"anemic\",\"ice man\"]]"));
    }

    @Test
    public void testExtraLoner() throws Exception {

        List<String> list = new ArrayList<String>();
        list.add("bad credit");
        list.add("cinema");
        list.add("anemic");
        list.add("debit card");
        list.add("ice man");
        list.add("loner x");

        String words = list.stream()
                .collect(Collectors.joining(","));

        System.out.println(words);


        this.mvc.perform(get("/api/anagram/list?words="+words)).andExpect(status().isOk())
                .andExpect(content().string("[[\"bad credit\",\"debit card\"],[\"cinema\",\"anemic\",\"ice man\"],[\"loner x\"]]"));
    }

    @Test
    public void testAllLoners() throws Exception {

        List<String> list = new ArrayList<String>();
        list.add("loner a");
        list.add("loner b");
        list.add("loner c");

        String words = list.stream()
                .collect(Collectors.joining(","));

        System.out.println(words);


        this.mvc.perform(get("/api/anagram/list?words="+words)).andExpect(status().isOk())
                .andExpect(content().string("[[\"loner a\"],[\"loner b\"],[\"loner c\"]]"));
    }

    @Test
    public void testLoner() throws Exception {

        List<String> list = new ArrayList<String>();
        list.add("loner a");

        String words = list.stream()
                .collect(Collectors.joining(","));

        System.out.println(words);


        this.mvc.perform(get("/api/anagram/list?words="+words)).andExpect(status().isOk())
                .andExpect(content().string("[[\"loner a\"]]"));
    }

    @Test
    public void testOneGroup() throws Exception {

        List<String> list = new ArrayList<String>();
        list.add("loner a");
        list.add("a loner");
        list.add("loaner");

        String words = list.stream()
                .collect(Collectors.joining(","));

        System.out.println(words);


        this.mvc.perform(get("/api/anagram/list?words="+words)).andExpect(status().isOk())
                .andExpect(content().string("[[\"loner a\",\"a loner\",\"loaner\"]]"));
    }
}
