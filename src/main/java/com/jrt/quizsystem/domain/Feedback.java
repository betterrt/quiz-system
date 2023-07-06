package com.jrt.quizsystem.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feedback {
   private int rating;
   private String message;
   private int userId;
   private Timestamp date;

}
