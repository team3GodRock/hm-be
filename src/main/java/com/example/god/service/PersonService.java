package com.example.god.service;

import com.example.god.domain.Person;
import com.example.god.dto.response.PersonInfoResponseDto;
import com.example.god.dto.response.PersonSimpleDto;
import com.example.god.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    @Transactional
    public Long join(Person person) {
        personRepository.save(person); // 새로 저장
        return person.getId();
    }

    public List<Person> findPeople(){
        return personRepository.findAll();
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

    public List<PersonSimpleDto> getAllPerson(){
        List<Person> persons =personRepository.findAll();
        return persons.stream().map(person -> convertPersontoSimpeDto(person)).toList();
    }

    public PersonSimpleDto convertPersontoSimpeDto(Person person){
        return new PersonSimpleDto(
                person.getId(),
                person.getName(),
                person.getPosition()
        );
    }
}
