package com.example.god.service;


import com.example.god.controller.PromiseController;
import com.example.god.domain.Person;
import com.example.god.domain.Promise;
import com.example.god.domain.Record;
import com.example.god.dto.response.PromiseDto;
import com.example.god.dto.response.RecordDto;
import com.example.god.repository.PersonRepository;
import com.example.god.repository.PromiseRepository;
import com.example.god.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromiseService {
    private final PromiseRepository promiseRepository;

    public List<PromiseDto> getPromises(Long person_id){

        List<Promise> promises = promiseRepository.findByPersonId(person_id);
        return promises.stream().map(promise -> convertpromisetoDto(promise)).toList();

    }

    public PromiseDto convertpromisetoDto(Promise promise){
        return new PromiseDto(
                promise.getId(),
                promise.getPromiseDetail(),
                promise.isHasPromise()
        );
    }

    public Long promiseJoin(Promise promise){
        promiseRepository.save(promise);
        return promise.getId();
    }
}
