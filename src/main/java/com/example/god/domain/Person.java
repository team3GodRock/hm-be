package com.example.god.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String position;

    @Column(nullable = false)
    private String affliation;

    @Column(nullable = false)
    private Float supporting;

    @Column(nullable = false)
    private Float achivement;


    @OneToMany(mappedBy = "person")
    private List<Record> records;

    @OneToMany(mappedBy = "person")
    private List<Promise> promises;

}