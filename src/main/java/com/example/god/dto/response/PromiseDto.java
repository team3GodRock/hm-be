package com.example.god.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PromiseDto {
    private Long id;
    private String promiseDetail;
    private Boolean hasPromise;
}
