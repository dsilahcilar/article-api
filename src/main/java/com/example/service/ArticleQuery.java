package com.example.service;

import com.example.converter.ArticleConverter;
import com.example.model.Article;
import com.example.model.Author;
import com.example.model.PublishDate;
import com.example.persistence.entity.ArticleEntity;
import com.example.persistence.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ArticleQuery {
    private final ArticleRepository articleRepository;
    private final ArticleConverter converter;

    @Cacheable("article")
    public Article article(Long id) {
        ArticleEntity articleEntity = articleRepository.findOne(id);
        return converter.toModel(articleEntity);
    }

    public List<Article> articles(Pageable pageable) {
        Page<ArticleEntity> articleEntities = articleRepository.findAll(pageable);
        return converter.toModelList(articleEntities.getContent());
    }

    public List<Article> findByAuthor(Author author, Pageable pageable) {
        if (author.getId() != null) {
            return converter.toModelList(articleRepository.findByAuthorsId(
                    author.getId(),
                    pageable).getContent());
        }
        if (author.getName() != null) {
            if (author.getLastName() != null) {
                return converter.toModelList(articleRepository.findByAuthorsNameAndAuthorsLastName(
                        author.getName(),
                        author.getLastName(),
                        pageable).getContent()
                );
            } else {
                return converter.toModelList(articleRepository.findByAuthorsName(
                        author.getName(),
                        pageable).getContent());
            }
        } else if (author.getLastName() != null) {
            return converter.toModelList(articleRepository.findByAuthorsLastName(
                    author.getLastName(),
                    pageable).getContent());
        }
        return Collections.emptyList();
    }

    public List<Article> findByPublishDate(PublishDate publishDate, Pageable pageable) {
        List<ArticleEntity> articles = articleRepository.findByPublishDateBetween(
                publishDate.getGte(),
                publishDate.getLte(), pageable).getContent();
        return converter.toModelList(articles);
    }

    public List<Article> findByKeywords(List<String> keywords, Pageable pageable) {
        List<ArticleEntity> articleEntities = articleRepository.findByKeywordsIn(keywords, pageable).getContent();
        return converter.toModelList(articleEntities);
    }
}
