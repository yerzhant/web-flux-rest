package com.example.webfluxrest.infra;

import com.example.webfluxrest.domain.Contact;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ContactRepository extends ReactiveMongoRepository<Contact, String> {
}
