package com.example.god.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecordDto {
    private Long id;
    private Long personID;
    private String history;
}
