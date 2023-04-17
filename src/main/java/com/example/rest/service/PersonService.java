package com.example.rest.service;

import com.example.rest.model.Person;
import com.example.rest.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void savePerson(Person person){
        personRepository.save(person);
    }

    @Transactional
    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    @Transactional
    public void deletePerson(int id){
        personRepository.deleteById(id);
    }

    @Transactional
    public Person findById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updatePerson(Person updateperson){
        Person person = personRepository.findById(updateperson.getId()).orElse(null);
        person.setName(updateperson.getName());
        person.setEmail(updateperson.getEmail());
        personRepository.save(person);
    }

}
