package ru.web.exam.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.web.exam.models.Person;
import ru.web.exam.services.PeopleService;

@Component
public class PeopleValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PeopleValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        try {
            peopleService.loadUserByUsername(person.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return; // так и должно быть, пользователь не найден
        }

        errors.rejectValue("name",
                "Error message",
                "Человек с таким именем уже существует");
    }
}
