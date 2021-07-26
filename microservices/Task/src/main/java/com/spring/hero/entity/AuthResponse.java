package com.spring.hero.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	private String uid;
	private String name;
	private boolean isValid;
}
