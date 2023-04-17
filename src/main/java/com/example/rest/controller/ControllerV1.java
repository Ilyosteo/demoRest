package com.example.rest.controller;

import com.example.rest.model.Person;
import com.example.rest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
public class ControllerV1 {

    private PersonService personService;

    @Autowired
    public ControllerV1(PersonService personService) {
        this.personService = personService;
    }


    // http://localhost:8080/person - по этому запросу ты отправляешь на сервер данные
    @PostMapping()
    public ResponseEntity<HttpStatus> savePerson(@RequestBody Person person){
        personService.savePerson(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // http://localhost:8080/person - по этому запросу ты запрашиваешь все данные
    @GetMapping()
    public List<Person> getPersonAll(){
        return personService.getAllPerson();
    }

    // http://localhost:8080/person/id - по этому запросу удаляешь данные
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delPer(@PathVariable("id") int id){
        personService.deletePerson(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // http://localhost:8080/person/id - по этому запросу ты меняешь данные name, email
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> upPerson(@RequestBody Person person, @PathVariable("id") int id){
        person.setId(id);
        personService.updatePerson(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // http://localhost:8080/person/id - по этому запросу ты ищешь человека по id
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id){
        return personService.findById(id);
    }

}
