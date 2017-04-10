package com.example.model.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class ConflictException extends RuntimeException {
    private final String message;
    private final String description;
}
