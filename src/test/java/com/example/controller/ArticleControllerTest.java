package com.example.controller;

import com.example.model.Article;
import com.example.model.exception.ConflictException;
import com.example.model.exception.NotFoundException;
import com.example.service.ArticleCommand;
import com.example.service.ArticleQuery;
import com.example.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ArticleCommand articleCommand;
    @MockBean
    private ArticleQuery articleQuery;

    @Test
    public void updateShouldReturn404WhenNotFoundExceptionThrowed() throws Exception {
        Article article = Article.builder()
                .text("First article text")
                .header("First article header")
                .shortDescription("First article description")
                .publishDate(LocalDateTime.now())
                .authors(Arrays.asList(1L))
                .keywords(Arrays.asList("first", "martin", "fowler", "microservice"))
                .build();

        willThrow(new NotFoundException("")).given(articleCommand).update(1L, article);

        this.mvc.perform(put("/articles/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(article))
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void updateShouldReturn200WhenUpdateSuccessful() throws Exception {
        Article article = Article.builder()
                .text("First article text")
                .header("First article header")
                .shortDescription("First article description")
                .publishDate(LocalDateTime.now())
                .authors(Arrays.asList(1L))
                .keywords(Arrays.asList("first", "martin", "fowler", "microservice"))
                .build();

        this.mvc.perform(put("/articles/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(article))
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void updateShouldReturnConflictWhenConflictExceptionThrowed() throws Exception {
        Article article = Article.builder()
                .text("First article text")
                .header("First article header")
                .shortDescription("First article description")
                .publishDate(LocalDateTime.now())
                .authors(Arrays.asList(1L))
                .keywords(Arrays.asList("first", "martin", "fowler", "microservice"))
                .build();

        willThrow(new ConflictException("","")).given(articleCommand).update(1L, article);

        this.mvc.perform(put("/articles/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(article))
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(409));

    }

}