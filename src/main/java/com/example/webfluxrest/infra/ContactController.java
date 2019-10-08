package com.example.webfluxrest.infra;

import com.example.webfluxrest.domain.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepository repository;

    public ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Contact> getAll() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Contact> add(@Valid @RequestBody Contact contact) {
        return repository.save(contact);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Contact>> update(@PathVariable("id") String id, @Valid @RequestBody Contact contact) {
        return repository.findById(id)
                .flatMap(c -> {
                    c.setUserName(contact.getUserName());
                    c.setEmail(contact.getEmail());
                    c.setPhoneNumber(contact.getPhoneNumber());
                    return repository.save(c);
                })
                .map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
