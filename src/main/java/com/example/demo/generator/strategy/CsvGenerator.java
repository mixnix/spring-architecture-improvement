package com.example.demo.generator.strategy;

import com.example.demo.generator.domain.FileType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CsvGenerator implements StrategyGenerator{
    @Override
    public FileType getType() {
        return FileType.CSV;
    }

    @Override
    public void generateType() {
        log.info("CSV");
    }
}
