package com.example.god.runner;

import com.example.god.domain.Promise;
import com.example.god.domain.Person;
import com.example.god.repository.PersonRepository;
import com.example.god.repository.PromiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
@RequiredArgsConstructor
@DependsOn("personQueryRunner")
public class PromiseQueryRunner implements CommandLineRunner {

    private final PromiseRepository promiseRepository;
    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        String fileName = "src/main/resources/promise.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                String name = tokens[0].trim();
                Person person = personRepository.findByName(name);
                Promise promise = new Promise();
                promise.setPerson(person);
                promise.setPromiseDetail(tokens[1].trim());
                promise.setHasPromise(Boolean.parseBoolean(tokens[2].trim()));

                promiseRepository.save(promise);
            }
        } catch (Exception e) {
            System.err.println("Error processing promise data: " + e.getMessage());
        }
    }
}
