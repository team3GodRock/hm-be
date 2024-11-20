package com.example.god.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Promise {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "promise_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(length = 500)
    private String promiseDetail;

    @Column(nullable = false)
    private boolean hasPromise;

}
