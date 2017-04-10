package com.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublishDate {
    private LocalDateTime gte;
    private LocalDateTime lte;
}
