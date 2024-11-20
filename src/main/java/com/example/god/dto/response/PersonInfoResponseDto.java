package com.example.god.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PersonInfoResponseDto {
    private Long id;
    private String name;
    private String position;
    private String affliation;
    private float supporting;
    private float achivement;

}
