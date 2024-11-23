package com.example.god.controller;

import com.example.god.domain.Person;

import com.example.god.domain.Record;
import com.example.god.service.PersonService;

import com.example.god.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminRecordController {
    private final RecordService recordService;
    private final PersonService personService;

    @GetMapping("/admin/record/new")
    public String createForm(Model model) {
        List<Person> members = personService.findPeople();
        model.addAttribute("members", members); // 인물 목록 추가
        model.addAttribute("recordForm", new RecordForm()); // recordForm 객체 추가
        return "record/createRecordForm"; // 폼의 HTML 파일 이름
    }



    @PostMapping("/admin/record/new")
    public String createPromise(@ModelAttribute RecordForm recordForm) {
        Person person = personService.findOne(recordForm.getPersonId());
        if (person == null) {
            throw new IllegalArgumentException("인물을 찾을 수 없습니다. ID: " + recordForm.getPersonId());
        }

        // record 객체 생성 및 값 설정
        Record record =new Record();
        record.setPerson(person);
        record.setHistory(recordForm.getHistory());

        // 저장
        recordService.recordJoin(record);
        return "redirect:/admin";
    }

}
