package com.example.webfluxrest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    private String id;

    @NotBlank
    @NotNull
    private String userName;

    @Email
    private String email;

    @Pattern(regexp = "\\+\\d \\d{3} \\d{3} \\d{4}")
    private String phoneNumber;
}
