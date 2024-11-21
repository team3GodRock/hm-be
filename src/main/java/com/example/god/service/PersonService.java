package com.example.god.service;

import com.example.god.domain.Person;
import com.example.god.dto.response.PersonInfoResponseDto;
import com.example.god.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public PersonInfoResponseDto  getPersonInfo(String position){
        Person person = personRepository.findByPosition(position);
        return convertPersonToInfoDto(person);

    }

    public PersonInfoResponseDto convertPersonToInfoDto(Person person){
        return new PersonInfoResponseDto(
                person.getId(),
                person.getName(),
                person.getPosition(),
                person.getAffiliation(),
                person.getSupporting(),
                person.getAchievement()

        );
    }

}
