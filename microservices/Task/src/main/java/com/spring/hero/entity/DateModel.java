package com.spring.hero.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateModel {
	 @DateTimeFormat(pattern = "yyyy-mm-dd")
	Date date;
}
