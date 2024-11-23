package com.example.god.repository;

import com.example.god.domain.Promise;
import com.example.god.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromiseRepository extends JpaRepository<Promise,Long> {
    List<Promise> findByPersonId(Long id);

    // 특정 인물의 달성된 공약 수 조회
    @Query("SELECT COUNT(p) FROM Promise p WHERE p.person.id = :personId AND p.hasPromise = true")
    Long countAchievedPromisesByPersonId(@Param("personId") Long personId);

    // 특정 인물의 전체 공약 수 조회
    @Query("SELECT COUNT(p) FROM Promise p WHERE p.person.id = :personId")
    Long countTotalPromisesByPersonId(@Param("personId") Long personId);
}
