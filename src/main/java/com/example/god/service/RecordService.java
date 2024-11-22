package com.example.god.service;

import com.example.god.domain.Person;
import com.example.god.domain.Record;
import com.example.god.dto.response.PersonInfoResponseDto;
import com.example.god.dto.response.RecordDto;
import com.example.god.repository.PersonRepository;
import com.example.god.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final PersonRepository personRepository;

    public List<RecordDto> getRecordInfo(Long person_id){

        List<Record> records = recordRepository.findByPersonId(person_id);
        return records.stream().map(record -> convertRecordtoDto(record)).toList();

    }

    public RecordDto convertRecordtoDto(Record record){
       return new RecordDto(
               record.getId(),
               record.getPerson().getId(),
               record.getHistory()
       );
    }

}
