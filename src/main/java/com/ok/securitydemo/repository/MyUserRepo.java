package com.ok.securitydemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepo extends JpaRepository<MyUser, Long> {

}
