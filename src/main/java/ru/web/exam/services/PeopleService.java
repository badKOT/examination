package ru.web.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.web.exam.models.Person;
import ru.web.exam.repositories.PeopleRepository;
import ru.web.exam.security.PersonDetails;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService implements UserDetailsService {
    private final PeopleRepository rep;

    @Autowired
    public PeopleService(PeopleRepository rep) {
        this.rep = rep;
    }

    public List<Person> findAll() {
        return rep.findAll();
    }

    public Person findOne(int id) {
        return rep.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        rep.save(person);
    }

    @Transactional
    public void update(int id, Person newPerson) {
        newPerson.setId(id);
        Optional<Person> oldPerson = rep.findById(id);
        newPerson.setPassword(oldPerson.get().getPassword());
        newPerson.setRole(oldPerson.get().getRole());
        rep.save(newPerson);
    }

    @Transactional
    public void delete(int id) { rep.deleteById(id); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = rep.findByUsername(username);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }
}