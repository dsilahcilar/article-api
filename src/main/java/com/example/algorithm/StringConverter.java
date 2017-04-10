package com.example.algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringConverter {

    public OperationList convert(final String source, final String target) {
        return convert(new StringBuffer(source), new StringBuffer(target), 0, new OperationList());
    }

    private OperationList convert(final StringBuffer source, final StringBuffer target, int count,
                                  OperationList operations) {
        log.info("Count: {}", ++count);
        int removeIndex = indexOfRemoveItem(source, target);
        char c = source.charAt(removeIndex);
        log.info("char: {}, at: {} : will be removed", c, removeIndex);
        operations.add(new Operation(removeIndex, c, OperationType.DELETE));
        source.deleteCharAt(removeIndex);
        log.info("After remove newSource is {}", source);
        int putIndex = indexOfPutItem(c, source, target, 0);
        if (putIndex != -1) {
            log.info("char: {}, at: {} : will be putted", c, putIndex);
            operations.add(new Operation(putIndex, c, OperationType.PUT));
            source.insert(putIndex, c);
        }

        if (!source.toString().equals(target.toString())) {
            log.info("source: {}", source);
            log.info("target: {}", target);
            return convert(source, target, count, operations);
        }
        return operations;
    }

    private int indexOfRemoveItem(StringBuffer source, StringBuffer target) {
        for (int i = 0; i < target.length(); i++) {
            if (source.charAt(i) != target.charAt(i)) {
                return source.lastIndexOf(String.valueOf(target.charAt(i)));
            }
        }

        if (source.length() > target.length()) {
            return target.length();
        }
        return -1;
    }

    private int indexOfPutItem(char c, StringBuffer source, StringBuffer target, int firstIndex) {
        int index = target.indexOf((String.valueOf(c)), firstIndex);
        if (index == -1) {
            return -1;
        }
        if (source.length() > index && source.charAt(index) == target.charAt(index)) {
            return indexOfPutItem(c, source, target, index + 1);
        }
        return index;
    }
}
