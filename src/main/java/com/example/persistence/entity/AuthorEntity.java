package com.example.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "AUTHOR_ID_SEQ", allocationSize = 1, sequenceName = "AUTHOR_ID_SEQ")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_ID_SEQ")
    private Long id;
    @ManyToMany(mappedBy = "authors")
    private Collection<ArticleEntity> articles;
    private String name;
    private String lastName;
}
