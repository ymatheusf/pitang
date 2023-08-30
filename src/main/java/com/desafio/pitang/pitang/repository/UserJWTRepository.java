package com.desafio.pitang.pitang.repository;

import com.desafio.pitang.pitang.entity.UserPitang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJWTRepository extends JpaRepository<UserPitang, String> {

     UserDetails findByLogin(String login);
}
