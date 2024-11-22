package com.example.god.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfoResponseDto {
    private Long id;
    private String name;
    private String position;
    private String affiliation;
    private float supporting;
    private float achievement;
}
