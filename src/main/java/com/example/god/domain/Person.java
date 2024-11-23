package com.example.god.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter @Setter
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String affiliation;

    @Column(nullable = false)
    private Float supporting;

    @Column(nullable = false)
    private Float achievement;

    private String photo;

    @OneToMany(mappedBy = "person")
    private List<Record> records;

    @OneToMany(mappedBy = "person")
    private List<Promise> promises;

}