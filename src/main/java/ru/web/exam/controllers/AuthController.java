package ru.web.exam.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.exam.models.Person;
import ru.web.exam.security.PersonDetails;
import ru.web.exam.services.PeopleService;
import ru.web.exam.services.RegistrationService;
import ru.web.exam.util.PeopleValidator;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PeopleValidator peopleValidator;
    private final RegistrationService registrationService;
    private final PeopleService peopleService;

    @Autowired
    public AuthController(PeopleValidator peopleValidator,
                          RegistrationService registrationService,
                          PeopleService peopleService) {
        this.peopleValidator = peopleValidator;
        this.registrationService = registrationService;
        this.peopleService = peopleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/error")
    public String error() {
        return "redirect:/login?error";
    }

    @GetMapping("/new")
    public String newUserPage(@ModelAttribute("person") Person person) {
        return "/auth/new";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("person") @Valid Person person,
                          BindingResult br, Principal principal) {
        peopleValidator.validate(person, br);
        if (br.hasErrors())
            return "/auth/new";

        registrationService.register(person);

//        // Authenticate the user
//        UsernamePasswordAuthenticationToken authRequest =
//                new UsernamePasswordAuthenticationToken(person.getUsername(), person.getPassword());
//        Authentication authentication = authenticationManager.authenticate(authRequest);
//
//        // Set the authentication in the SecurityContextHolder
//        SecurityContextHolder.getContext().setAuthentication(authentication);

//        PersonDetails _personDetails = (PersonDetails) peopleService.loadUserByUsername(principal.getName());
//        Person currentUser = personDetails.getPerson();
//        if (Objects.equals(currentUser.getRole(), "ROLE_ADMIN"))
//            return "redirect:/auth/admin";

        return "redirect:/auth/hello";
    }

    @GetMapping("/hello")
    public String helloPage() {
        return "auth/hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "auth/adminStart";
    }
}
