package com.example.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "article")
@SequenceGenerator(name = "ARTICLE_ID_SEQ", allocationSize = 1, sequenceName = "ARTICLE_ID_SEQ")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_ID_SEQ")
    private Long id;
    private String header;
    private String shortDescription;
    //@Lob TODO fix it : Large Objects may not be used in auto-commit mode' ...
    @Column
    private String text;
    private LocalDateTime publishDate;
    @ManyToMany
    @JoinTable(name = "article_author",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private List<AuthorEntity> authors;
    @ElementCollection
    @JoinTable(name = "article_keyword", joinColumns = @JoinColumn(name = "article_id"))
    private List<String> keywords;

    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
}
