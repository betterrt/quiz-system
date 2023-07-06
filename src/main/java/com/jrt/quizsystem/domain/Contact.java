package com.jrt.quizsystem.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
   private String firstname;
   private String lastname;
   private String subject;
   private String message;
}
