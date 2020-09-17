package com.example.demo.generator.strategy;

import com.example.demo.generator.domain.FileType;

public interface StrategyGenerator {
    FileType getType();
    void generateType();
}
