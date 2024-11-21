package com.example.god.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "record")
@NoArgsConstructor
@Getter @Setter
public class Record {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(length = 500)
    private String history;
}