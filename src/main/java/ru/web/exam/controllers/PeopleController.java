package ru.web.exam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.exam.models.Person;
import ru.web.exam.security.PersonDetails;
import ru.web.exam.services.PeopleService;

import java.security.Principal;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/account")
    public String accountPage(Model model, Principal principal) {
        PersonDetails details = (PersonDetails) peopleService.loadUserByUsername(principal.getName());
        Person person = details.getPerson();
        model.addAttribute("person", person);

        return "people/account";
    }
}
