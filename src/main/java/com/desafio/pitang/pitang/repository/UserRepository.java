package com.desafio.pitang.pitang.repository;

import com.desafio.pitang.pitang.entity.UserPitang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPitang, Long> {
     UserDetails findByLogin(String login);

     @Query(
             value = "SELECT * FROM table_user ut where ut.login = :login", nativeQuery = true
     )
     UserPitang getByLogin(String login);

     UserPitang findByEmail(String email);
}
