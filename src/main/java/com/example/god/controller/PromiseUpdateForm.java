package com.example.god.controller;

import com.example.god.dto.response.PromiseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PromiseUpdateForm {
    private List<PromiseDto> promises; // 공약 리스트
}
