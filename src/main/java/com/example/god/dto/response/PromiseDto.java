package com.example.god.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PromiseDto {
    private Long id;
    private String promiseDetail;
    private Boolean hasPromise;
}
