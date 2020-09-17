package com.example.demo.generator;

import com.example.demo.generator.domain.FileType;
import com.example.demo.generator.strategy.StrategyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GeneratorFactory {

    private final List<StrategyGenerator> strategyGeneratorList;

    private Map<FileType, StrategyGenerator> strategyMap;

    @PostConstruct
    void init(){
        //todo: zamiast e->e rob Function.identity
        //todo: przenos do nastepnej lini na kropkach
        strategyMap = strategyGeneratorList.stream()
                .collect(Collectors.toMap(StrategyGenerator::getType, Function.identity()));
    }

    public StrategyGenerator getByKey(FileType fileType){
        return strategyMap.get(fileType);
    }
}
