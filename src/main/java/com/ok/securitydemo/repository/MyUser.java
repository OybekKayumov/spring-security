package com.ok.securitydemo.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class MyUser {

	@Id
	@GeneratedValue
	private long id;
	private String username;
	private String password;
	private Set<String> roles;
}
