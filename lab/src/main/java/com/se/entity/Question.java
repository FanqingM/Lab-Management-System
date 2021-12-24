package com.se.entity;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

@Data
public class Question {
    String questionId;
    String question;
    String a;
    String b;
    String c;
    String d;
    Integer answer;

}
