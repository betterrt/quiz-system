package com.jrt.quizsystem.domain.quiz;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
   private int id;
   private String name;
   private String imageUrl;
}
