package com.example.god.controller;

import com.example.god.domain.Person;
import com.example.god.dto.response.PersonInfoResponseDto;
import com.example.god.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminPersonController {
    private final PersonService personService;

    @GetMapping("/admin/person/new")
    public String createForm(Model model){
        model.addAttribute("personDTO",new PersonInfoResponseDto());
        return "person/createPersonDTO";
    }

    @PostMapping("/admin/person/new")
    public String create(@ModelAttribute PersonInfoResponseDto personInfoResponseDto){
        Person person = new Person();
        person.setName(personInfoResponseDto.getName());
        person.setAffiliation(personInfoResponseDto.getAffiliation());
        person.setAchievement(personInfoResponseDto.getAchievement());
        person.setPosition(personInfoResponseDto.getPosition());
        person.setSupporting(personInfoResponseDto.getSupporting());
        person.setPhoto(null);
        personService.join(person);

        return "redirect:/admin";
    }

    @GetMapping("/admin/people")
    public String list(Model model){
        List<Person> people = personService.findPeople();
        model.addAttribute("people",people);
        return "person/peopleList";
    }
}
