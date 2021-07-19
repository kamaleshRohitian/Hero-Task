package com.spring.hero.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionMessage {
    private Date date;
    private String message;
    private String details;

}
