package com.example.god.controller;


import com.example.god.dto.response.PersonInfoResponseDto;
import com.example.god.dto.response.PersonSimpleDto;
import com.example.god.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/person/{person_id}")
    public ResponseEntity<PersonInfoResponseDto> getPeronInfo(@PathVariable Long person_id) {
        PersonInfoResponseDto personInfoResponseDto = personService.getPersonInfo(person_id);

        return ResponseEntity.ok(personInfoResponseDto);
    }

    @GetMapping("/person/personList")
    public ResponseEntity<List<PersonSimpleDto>> getPeronInfo() {
        List<PersonSimpleDto> persons =personService.getAllPerson();

        return ResponseEntity.ok(persons);
    }

}
