package com.spring.emailsender.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.emailsender.entity.Email;


public interface EmailRepository extends JpaRepository<Email, Long> {
}