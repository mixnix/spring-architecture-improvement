package com.example.demo.flyweight.strategy;

import com.example.demo.generator.domain.FileType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CsvGenerator2 implements StrategyGenerator2{
    @Override
    public FileType getType() {
        return FileType.CSV;
    }

    @Override
    public void generateType() {
        log.info("CSV");
    }
}
