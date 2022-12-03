package com.gradle.restapi.controller.common;


import com.gradle.restapi.constant.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/common")
public class CommonController {
    @GetMapping("diamondStatus")
    public ResponseEntity<List<String>> getAllDiamondStatus(){
        List<String> status = Stream.of(Status.values()).map(Status::name).collect(Collectors.toList());
        return ResponseEntity.ok(status);
    }
}
