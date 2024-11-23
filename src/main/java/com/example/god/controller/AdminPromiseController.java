package com.example.god.controller;

import com.example.god.domain.Person;
import com.example.god.domain.Promise;
import com.example.god.service.PersonService;
import com.example.god.service.PromiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminPromiseController {
    private final PromiseService promiseService;
    private final PersonService personService;

    @GetMapping("/admin/promise/new")
    public String createForm(Model model) {
        List<Person> members = personService.findPeople();
        model.addAttribute("members", members); // 인물 목록 추가
        model.addAttribute("promiseForm", new PromiseForm()); // PromiseForm 객체 추가
        return "promise/createPromiseForm"; // 폼의 HTML 파일 이름
    }



    @PostMapping("/admin/promise/new")
    public String createPromise(@ModelAttribute PromiseForm promiseForm) {
        Person person = personService.findOne(promiseForm.getPersonId());
        if (person == null) {
            throw new IllegalArgumentException("인물을 찾을 수 없습니다. ID: " + promiseForm.getPersonId());
        }

        // Promise 객체 생성 및 값 설정
        Promise promise = new Promise();
        promise.setPerson(person);
        promise.setPromiseDetail(promiseForm.getPromiseDetail());
        promise.setHasPromise(promiseForm.getHasPromise());

        // 저장
        promiseService.promiseJoin(promise);
        return "redirect:/admin";
    }

}
