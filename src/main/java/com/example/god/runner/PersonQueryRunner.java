package com.example.god.runner;

import com.example.god.domain.Person;
import com.example.god.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(1)
public class PersonQueryRunner implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        // CSV 파일 읽기
        String fileName = "src/main/resources/person.csv"; // CSV 파일 경로
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // 헤더 스킵
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                if (tokens.length < 6) {
                    System.err.println("Invalid line format: " + line);
                    continue; // 데이터 부족 시 스킵
                }

                // Person 객체 생성 및 필드 초기화
                Person person = new Person();
                person.setName(tokens[0].trim()); // 이름
                person.setPosition(tokens[1].trim()); // 직책
                person.setAffiliation(tokens[2].trim()); // 소속
                person.setSupporting(Float.parseFloat(tokens[3].trim())); // 지지율
                person.setAchievement(Float.parseFloat(tokens[4].trim())); // 공약 달성도
                person.setPhoto(tokens[5].trim()); // 사진

                // 데이터 저장
                personRepository.save(person);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

    }
}
