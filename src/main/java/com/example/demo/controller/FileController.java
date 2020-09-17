package com.example.demo.controller;

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

    //todo: wywolujemy to uzywajac requesta: http://localhost:8080/api/files?fileType=CSV
    //todo: lub: localhost:8080/api/files?fileType=PDF
    @GetMapping
    void getRandomFile(@RequestParam FileType fileType){
        generatorFactory.getByKey(fileType).generateType();
    }
}
