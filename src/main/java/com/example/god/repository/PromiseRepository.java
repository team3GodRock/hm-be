package com.example.god.repository;

import com.example.god.domain.Promise;
import com.example.god.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromiseRepository extends JpaRepository<Promise,Long> {
    List<Promise> findByPersonId(Long id);
}
