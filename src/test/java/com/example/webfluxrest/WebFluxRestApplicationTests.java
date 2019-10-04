package com.example.webfluxrest;

import com.example.webfluxrest.domain.Contact;
import com.example.webfluxrest.infra.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebFluxRestApplicationTests {

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private ContactRepository repository;

    @Test
    public void testCreate() {
        Contact contact = new Contact();
        contact.setUserName("123");
        contact.setEmail("abc@xyz.kz");

        testClient.post().uri("/contacts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(contact), Contact.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.userName").isEqualTo("123")
                .jsonPath("$.email").isEqualTo("abc@xyz.kz");
    }

}
