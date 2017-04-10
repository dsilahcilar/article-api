package com.example.configuration;

import com.example.converter.ArticleConverter;
import com.example.converter.AuthorConverter;
import com.example.persistence.repository.ArticleRepository;
import com.example.persistence.repository.AuthorRepository;
import com.example.service.ArticleCommand;
import com.example.service.ArticleQuery;
import com.example.service.AuthorService;
import com.example.service.SearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class ApplicationConfiguration {

    @Bean
    public ArticleQuery articleService(ArticleRepository articleRepository, ArticleConverter articleConverter) {
        return new ArticleQuery(articleRepository, articleConverter);
    }

    @Bean
    public AuthorService authorService(AuthorRepository authorRepository, AuthorConverter authorConverter) {
        return new AuthorService(authorRepository, authorConverter);
    }

    @Bean
    public ArticleCommand articleCommand(ArticleRepository articleRepository, ArticleConverter articleConverter) {
        return new ArticleCommand(articleRepository, articleConverter);
    }

    @Bean
    public SearchService searchService(ArticleQuery articleQuery) {
        return new SearchService(articleQuery);
    }

    @Bean
    public ArticleConverter articleConverter() {
        return new ArticleConverter();
    }

    @Bean
    public AuthorConverter authorConverter() {
        return new AuthorConverter();
    }
}
