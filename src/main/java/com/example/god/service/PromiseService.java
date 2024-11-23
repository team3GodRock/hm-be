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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromiseService {
    private final PromiseRepository promiseRepository;

    public List<PromiseDto> getPromises(Long person_id) {
        List<Promise> promises = promiseRepository.findByPersonId(person_id);
        return promises.stream().map(promise -> convertpromisetoDto(promise)).toList();
    }

    public List<Promise> findPromisesByPersonId(Long personId) {
        return promiseRepository.findByPersonId(personId);
    }

    public PromiseDto convertpromisetoDto(Promise promise) {
        return new PromiseDto(
                promise.getId(),
                promise.getPromiseDetail(),
                promise.isHasPromise()
        );
    }

    public Long promiseJoin(Promise promise) {
        promiseRepository.save(promise);
        return promise.getId();
    }

    public List<Promise> findPromises() {
        return promiseRepository.findAll();
    }

    @Transactional
    public void updatePromiseStatus(Long id, boolean hasPromise) {
        Promise promise = promiseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공약이 존재하지 않습니다. ID: " + id));
        promise.setHasPromise(hasPromise);
        promiseRepository.save(promise);
    }

    public Person findPersonByPromiseId(Long promiseId) {
        Promise promise = promiseRepository.findById(promiseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Promise ID: " + promiseId));
        return promise.getPerson();
    }
}