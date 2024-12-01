package com.ok.securitydemo.repository;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class MyUser {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String username;

	private String password;

	private Set<String> roles;
}
