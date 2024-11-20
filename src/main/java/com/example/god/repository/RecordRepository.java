package com.example.god.repository;

import com.example.god.domain.Person;
import com.example.god.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByPersonId(Long id);
}
