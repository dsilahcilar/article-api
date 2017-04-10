package com.example.model.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {
    private final String message;
}
