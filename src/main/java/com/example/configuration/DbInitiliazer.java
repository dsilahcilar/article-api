package com.example.configuration;

import com.example.model.Article;
import com.example.model.Author;
import com.example.service.ArticleCommand;
import com.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DbInitiliazer implements CommandLineRunner {
    @Autowired
    private ArticleCommand articleCommand;
    @Autowired
    private AuthorService authorService;

    @Override
    public void run(String... strings) throws Exception {
        Author author = Author.builder()
                .name("Martin")
                .lastName("Fowler")
                .build();

        Author author2 = Author.builder()
                .name("Kent")
                .lastName("Beck")
                .build();

        Author author3 = Author.builder()
                .name("Kent")
                .lastName("Clark")
                .build();

        Author author4 = Author.builder()
                .name("Kathy")
                .lastName("Sierra")
                .build();

        Author author5 = Author.builder()
                .name("Elisabeth")
                .lastName("Freeman")
                .build();

        authorService.create(author);
        authorService.create(author2);
        authorService.create(author3);
        authorService.create(author4);
        authorService.create(author5);

        Article article1 = Article.builder()
                .text("First article text")
                .header("First article header")
                .shortDescription("First article description")
                .publishDate(LocalDateTime.now())
                .authors(Arrays.asList(1L))
                .keywords(Arrays.asList("first", "martin", "fowler", "microservice"))
                .build();

        Article article2 = Article.builder()
                .text("Second article text")
                .header("Second article header")
                .shortDescription("Second article description")
                .publishDate(LocalDateTime.now())
                .authors(Arrays.asList(2L))
                .keywords(Arrays.asList("second", "kent", "tdd", "microservice"))
                .build();

        Article article3 = Article.builder()
                .text("Third article text")
                .header("Third article header")
                .shortDescription("Third article description")
                .publishDate(LocalDateTime.now())
                .authors(Arrays.asList(3L))
                .keywords(Arrays.asList("third", "kent", "superman", "krypton"))
                .build();

        Article article4 = Article.builder()
                .text("Head First Design Patterns text")
                .header("Head First Design Patterns")
                .shortDescription("Desing patterns")
                .publishDate(LocalDateTime.now())
                .authors(Arrays.asList(4L, 5L))
                .keywords(Arrays.asList("fourth", "head", "first", "design patterns", "factory"))
                .build();

        articleCommand.save(article1);
        articleCommand.save(article2);
        articleCommand.save(article3);
        articleCommand.save(article4);

    }
}
