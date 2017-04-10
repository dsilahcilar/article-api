package com.example.service;

import com.example.model.Article;
import com.example.model.Author;
import com.example.model.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class SearchService {
    private final ArticleQuery articleQuery;

    public List<Article> search(Query query, Pageable pageable) {
        Author author = query.getAuthor();
        if (author != null) {
            return articleQuery.findByAuthor(author, pageable);
        } else if (query.getPublishDate() != null) {
            return articleQuery.findByPublishDate(query.getPublishDate(), pageable);
        } else if (query.getKeywords() != null) {
            return articleQuery.findByKeywords(query.getKeywords(), pageable);
        }
        return articleQuery.articles(pageable);
    }
}
