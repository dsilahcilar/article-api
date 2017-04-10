package com.example.service;

import com.example.converter.ArticleConverter;
import com.example.model.Author;
import com.example.persistence.repository.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleQueryTest {
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleConverter converter;

    private ArticleQuery articleQuery;

    @Before
    public void init() {
        articleQuery = new ArticleQuery(articleRepository, converter);
    }

    @Test
    public void shouldCallFindByNameRepositoryMethodWhenOnlyAuthorNameIsSet() throws Exception {
        Author author = Author.builder().name("Martin").build();

        Pageable pageable = mock(Pageable.class);
        when(articleRepository.findByAuthorsName(author.getName(), pageable))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        articleQuery.findByAuthor(author, pageable);

        verify(articleRepository).findByAuthorsName(author.getName(), pageable);
    }

    @Test
    public void shouldCallFindByNameAndLastNameRepositoryMethodWhenAuthorNameAndLastNamesAreSet() throws Exception {
        Author author = Author.builder().name("Martin").lastName("Fowler").build();

        Pageable pageable = mock(Pageable.class);
        when(articleRepository.findByAuthorsNameAndAuthorsLastName(author.getName(), author.getLastName(), pageable))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        articleQuery.findByAuthor(author, pageable);

        verify(articleRepository).findByAuthorsNameAndAuthorsLastName(author.getName(), author.getLastName(), pageable);
    }

}