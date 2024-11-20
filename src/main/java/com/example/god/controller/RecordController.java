package com.example.god.controller;


import com.example.god.domain.Record;
import com.example.god.dto.response.PersonInfoResponseDto;
import com.example.god.dto.response.RecordDto;
import com.example.god.repository.PersonRepository;
import com.example.god.service.PersonService;
import com.example.god.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecordController {
    private final RecordService recordService;


    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService= recordService;
    }


    @GetMapping("/record/{position}")
    public ResponseEntity<List<RecordDto>> getPeronInfo(@PathVariable String position) {
        List<RecordDto> recordDtos = recordService.getRecordInfo(position);

        return ResponseEntity.ok(recordDtos);
    }

}
