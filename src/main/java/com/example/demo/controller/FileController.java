package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    @GetMapping
    public ResponseEntity<Object> getFile(@RequestParam String filename){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Accel-Redirect", "secure");
        httpHeaders.set("Location-File", filename);
        return ResponseEntity.ok().headers(httpHeaders).build();
    }
}
