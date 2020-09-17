package com.example.demo.flyweight.strategy;

import com.example.demo.flyweight.GenericStrategy;
import com.example.demo.generator.domain.FileType;

public interface StrategyGenerator2 extends GenericStrategy<FileType> {
    void generateType();
}
