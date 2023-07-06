package com.jrt.quizsystem.domain.quiz;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizQuestion {
   private int qqId;
   private int quizId;
   private int questionId;
   private int userChoiceId;
   private int orderNum;
}
