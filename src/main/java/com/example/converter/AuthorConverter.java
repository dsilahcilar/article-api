package com.example.converter;

import com.example.model.Author;
import com.example.persistence.entity.AuthorEntity;

public class AuthorConverter extends ListConverter<AuthorEntity, Author> {

    public Author toModel(AuthorEntity authorEntity) {
        return Author.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .lastName(authorEntity.getLastName())
                .build();
    }

    public AuthorEntity toEntity(Author author) {
        return AuthorEntity.builder()
                .id(author.getId())
                .name(author.getName())
                .lastName(author.getLastName())
                .build();
    }
}
