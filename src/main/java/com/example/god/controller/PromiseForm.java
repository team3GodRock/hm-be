package com.example.god.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PromiseForm {
    private Long personId;
    private String promiseDetail;
    private Boolean hasPromise;
}
