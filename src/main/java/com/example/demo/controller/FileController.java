package com.example.demo.controller;

import com.example.demo.flyweight.GenericFactory;
import com.example.demo.flyweight.strategy.StrategyGenerator2;
import com.example.demo.generator.GeneratorFactory;
import com.example.demo.generator.domain.FileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final GeneratorFactory generatorFactory;

    private final GenericFactory<FileType, StrategyGenerator2> genericFactory;

    //todo: wywolujemy to uzywajac requesta: http://localhost:8080/api/files?fileType=CSV
    //todo: lub: localhost:8080/api/files?fileType=PDF

    //todo: gdy cztery razy juz powtarzasz ifa to wtedy uzyj juz tego wzorca aby uniknac if-ów
    @GetMapping
    void getRandomFile(@RequestParam FileType fileType){
        generatorFactory.getByKey(fileType).generateType();
    }

    //todo: to samo tylko że z generalizacją
    @GetMapping("/2")
    void getRandomFile2(@RequestParam FileType fileType){
        genericFactory.getByKey(fileType).generateType();
    }
}

