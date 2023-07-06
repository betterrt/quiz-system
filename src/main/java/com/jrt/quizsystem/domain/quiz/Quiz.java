package com.jrt.quizsystem.domain.quiz;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
   private int quizId;
   private int userId;
   private int categoryId;
   private String name;
   private int score;
   private Timestamp startTime;
   private Timestamp endTime;

}
