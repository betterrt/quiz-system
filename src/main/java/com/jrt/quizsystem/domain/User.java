package com.jrt.quizsystem.domain;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Boolean isActive;
    private Boolean isAdmin;

}
