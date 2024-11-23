package com.example.god.controller;

import com.example.god.domain.Person;
import com.example.god.domain.Promise;
import com.example.god.dto.response.PromiseDto;
import com.example.god.service.PersonService;
import com.example.god.service.PromiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        personService.updateAchievement(person);
        return "redirect:/admin";
    }

    @GetMapping("/admin/promise/select/person")
    public String createEdit(Model model) {
        List<Person> people = personService.findPeople(); // 모든 인물 목록 조회
        model.addAttribute("people", people);
        return "promise/promiseEditSelectPerson";
    }

    @GetMapping("/admin/promise/edit/{id}")
    public String showPromiseEdit(@PathVariable Long id, Model model) {
        List<Promise> promises = promiseService.findPromisesByPersonId(id); // 선택된 인물의 공약 조회
        Person person = personService.findOne(id);

        model.addAttribute("person", person); // 선택된 인물 정보 전달
        model.addAttribute("promises", promises); // 공약 목록 전달
        return "promise/promiseEdit"; // 공약 수정 페이지
    }

    @PostMapping("/admin/promise/update/{id}")
    public String updatePromises(
            @PathVariable Long id,
            @RequestParam("hasPromise") Boolean hasPromise) {
        // 공약 상태 업데이트
        promiseService.updatePromiseStatus(id, hasPromise);

        // 연관된 인물의 성취도 업데이트
        Person person = promiseService.findPersonByPromiseId(id);
        if (person != null) {
            personService.updateAchievement(person);
        }

        return "redirect:/admin"; // 수정 후 관리자 페이지로 리다이렉트
    }
}
