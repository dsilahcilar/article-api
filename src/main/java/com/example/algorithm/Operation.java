package com.example.algorithm;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Operation {
    private final int index;
    private final char chr;
    private final OperationType type;
}
