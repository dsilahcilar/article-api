package com.example.service;

import com.example.converter.AuthorConverter;
import com.example.model.Author;
import com.example.persistence.entity.AuthorEntity;
import com.example.persistence.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorConverter converter;

    public void create(Author author) {
        AuthorEntity authorEntity = converter.toEntity(author);
        authorRepository.save(authorEntity);
    }

    public Author author(Long id) {
        AuthorEntity authorEntity = authorRepository.findOne(id);
        return converter.toModel(authorEntity);
    }

    public void delete(Long id) {
        authorRepository.delete(id);
    }

    public List<Author> authors() {
        return converter.toModelList(authorRepository.findAll());
    }
}
