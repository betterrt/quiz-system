package com.jrt.quizsystem.domain.quiz;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choice {
    private int choiceId;
    private int questionId;
    private String description;
    private Boolean isCorrect;
}