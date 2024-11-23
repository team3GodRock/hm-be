package com.example.god.service;

import com.example.god.domain.Person;
import com.example.god.dto.response.PersonInfoResponseDto;
import com.example.god.dto.response.PersonSimpleDto;
import com.example.god.repository.PersonRepository;
import com.example.god.repository.PromiseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PromiseRepository promiseRepository;

    @Transactional
    public Long join(Person person) {
        personRepository.save(person); // 새로 저장
        return person.getId();
    }

    @Transactional
    public void updateAchievement(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        Long personId = person.getId();
        Long achievedCount = promiseRepository.countAchievedPromisesByPersonId(personId);
        Long totalCount = promiseRepository.countTotalPromisesByPersonId(personId);
        float achieveRate = (totalCount > 0) ? (float) ((achievedCount * 100) / totalCount) : 0;
        achieveRate = Math.max(0, Math.min(achieveRate, 100));
        person.setAchievement(achieveRate);
        personRepository.save(person);
    }

    public List<Person> findPeople(){
        return personRepository.findAll();
    }

    public Person findOne(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 Person을 찾을 수 없습니다. ID: " + personId));
    }

    public PersonInfoResponseDto  getPersonInfo(Long person_id){
        Person person = personRepository.findById(person_id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " +person_id));
        return convertPersonToInfoDto(person);
    }

    public PersonInfoResponseDto convertPersonToInfoDto(Person person){
        return new PersonInfoResponseDto(
                person.getId(),
                person.getName(),
                person.getPosition(),
                person.getAffiliation(),
                person.getSupporting(),
                person.getAchievement(),
                person.getPhoto()
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

    @Transactional
    public boolean incrementLikes(Long personId) {
        // 데이터베이스에서 해당 ID의 인물을 조회
        Person person = personRepository.findById(personId).orElse(null);

        if (person != null) {
            person.setLike(person.getLike() + 1); // 좋아요 수 증가
            personRepository.save(person); // 업데이트된 데이터 저장
            return true;
        }
        return false; // 인물 정보가 없으면 false 반환
    }

    @Transactional
    public boolean incrementDislikes(Long personId) {
        Person person = personRepository.findById(personId).orElse(null);

        if (person != null) {
            person.setDislike(person.getDislike() + 1);
            personRepository.save(person);
            return true;
        }
        return false;
    }
}
