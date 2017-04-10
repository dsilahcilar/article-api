package com.example.algorithm;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class OperationList {
    private Integer counter = 1;
    @Getter
    private final Map<Integer, Operation> operations = new HashMap<>();

    public void add(Operation operation) {
        operations.put(counter++, operation);
    }
}
