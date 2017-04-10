package com.example.persistence.repository;

import com.example.persistence.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Page<ArticleEntity> findByAuthorsName(String name, Pageable pageable);

    Page<ArticleEntity> findByAuthorsNameAndAuthorsLastName(String name, String lastName, Pageable pageable);

    Page<ArticleEntity> findByAuthorsLastName(String lastName, Pageable pageable);

    Page<ArticleEntity> findByAuthorsId(Long id, Pageable pageable);

    Page<ArticleEntity> findByPublishDateBetween(LocalDateTime gte, LocalDateTime lte, Pageable pageable);

    Page<ArticleEntity> findByKeywordsIn(List<String> keywords, Pageable pageable);
}
