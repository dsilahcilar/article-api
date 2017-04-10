package com.example.converter;

import com.example.model.Article;
import com.example.persistence.entity.ArticleEntity;
import com.example.persistence.entity.AuthorEntity;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ArticleConverter extends ListConverter<ArticleEntity,Article> {

    public Article toModel(ArticleEntity articleEntity) {
        return Article.builder()
                .id(articleEntity.getId())
                .header(articleEntity.getHeader())
                .text(articleEntity.getText())
                .shortDescription(articleEntity.getShortDescription())
                .publishDate(articleEntity.getPublishDate())
                .authors(toAuthors(articleEntity.getAuthors()))
                .keywords(articleEntity.getKeywords())
                .build();
    }

    public ArticleEntity toEntity(Article article) {
        return ArticleEntity.builder()
                .id(article.getId())
                .header(article.getHeader())
                .text(article.getText())
                .shortDescription(article.getShortDescription())
                .authors(toAuthorsEntities(article.getAuthors()))
                .publishDate(article.getPublishDate())
                .keywords(article.getKeywords())
                .build();
    }

    private List<AuthorEntity> toAuthorsEntities(List<Long> ids) {
        List<AuthorEntity> authorEntities = new ArrayList<>();
        for (Long id : ids) {
            AuthorEntity authorEntity = AuthorEntity.builder().id(id).build();
            authorEntities.add(authorEntity);
        }
        return authorEntities;
    }

    private List<Long> toAuthors(List<AuthorEntity> authorEntities) {
        List<Long> authors = new ArrayList<>();
        for (AuthorEntity eachAuthor : authorEntities) {
            authors.add(eachAuthor.getId());
        }
        return authors;
    }

}
