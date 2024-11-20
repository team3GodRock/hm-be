package com.example.god.runner;

import com.example.god.repository.PersonRepository;
import com.example.god.repository.RecordRepository;
import com.example.god.domain.Record;
import com.example.god.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
@RequiredArgsConstructor
@DependsOn("personQueryRunner")
public class RecordQueryRunner implements CommandLineRunner {

    private final RecordRepository recordRepository;
    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        String fileName = "src/main/resources/record.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                String name = tokens[0].trim();
                Person person = personRepository.findByName(name);

                Record record = new Record();
                record.setPerson(person);
                record.setHistory(tokens[1].trim());

                recordRepository.save(record);
            }
        } catch (Exception e) {
            System.err.println("Error processing promise data: " + e.getMessage());
        }
    }
}
