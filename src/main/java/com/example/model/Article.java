package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {
    private Long id;
    private String header;
    private String shortDescription;
    private String text;
    private LocalDateTime publishDate;
    private List<Long> authors;
    private List<String> keywords;
}
