package com.example.god.controller;



import com.example.god.dto.response.PromiseDto;
import com.example.god.dto.response.RecordDto;
import com.example.god.service.PromiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PromiseController {
    private final PromiseService promiseService;


    @Autowired
    public PromiseController(PromiseService promiseService) {
        this.promiseService=promiseService;
    }

    @GetMapping("/promise/{person_id}")
    public ResponseEntity<List<PromiseDto>> getPeronInfo(@PathVariable Long person_id) {
        List<PromiseDto> promises = promiseService.getPromises(person_id);

        return ResponseEntity.ok(promises);
    }


}
