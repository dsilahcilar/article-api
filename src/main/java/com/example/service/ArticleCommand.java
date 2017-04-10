package com.example.service;

import com.example.converter.ArticleConverter;
import com.example.model.Article;
import com.example.model.exception.ConflictException;
import com.example.model.exception.NotFoundException;
import com.example.persistence.entity.ArticleEntity;
import com.example.persistence.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;

@Slf4j
@RequiredArgsConstructor
public class ArticleCommand {
    private final ArticleRepository articleRepository;
    private final ArticleConverter converter;

    public void save(Article article) {
        ArticleEntity articleEntity = converter.toEntity(article);
        articleRepository.save(articleEntity);
    }

    @CacheEvict("article")
    public void delete(Long id) {
        try {
            articleRepository.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            log.warn("Article: {} couldn't be deleted because it is not exists ", id);
        }
    }

    @CacheEvict(value = "article", key = "#id")
    public void update(Long id, Article article) {
        ArticleEntity foundedArticle = articleRepository.findOne(id);
        if (foundedArticle == null) {
            log.warn("Article could not found with id: {}", id);
            throw new NotFoundException("Article could not found with id: " + id);
        }

        ArticleEntity updateRequestEntity = converter.toEntity(article);
        if (updateRequestEntity.getId().equals(foundedArticle.getId())) {
            articleRepository.save(updateRequestEntity);
        } else {
            log.warn("Request id: {} does not matched with article id: {}", id, article.getId());
            throw new ConflictException("Article could not updated ", "Request id " + id
                    + " does not matched with article id: " + article.getId());
        }
    }

}
