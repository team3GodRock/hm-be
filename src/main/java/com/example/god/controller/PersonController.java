package com.example.god.controller;


import com.example.god.dto.response.PersonInfoResponseDto;
import com.example.god.dto.response.PersonSimpleDto;
import com.example.god.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/person/like/{person_id}")
    public ResponseEntity<String> likePerson(@PathVariable Long person_id) {
        // 특정 person_id의 좋아요 수 증가
        boolean success = personService.incrementLikes(person_id);

        if (success) {
            return ResponseEntity.ok("좋아요가 성공적으로 추가되었습니다.");
        } else {
            return ResponseEntity.status(404).body("해당 인물을 찾을 수 없습니다.");
        }
    }

    @PostMapping("/person/dislike/{person_id}")
    public ResponseEntity<String> dislikePerson(@PathVariable Long person_id) {
        // 특정 person_id의 좋아요 수 증가
        boolean success = personService.incrementDislikes(person_id);

        if (success) {
            return ResponseEntity.ok("싫어요가 성공적으로 추가되었습니다.");
        } else {
            return ResponseEntity.status(404).body("해당 인물을 찾을 수 없습니다.");
        }
    }
}
