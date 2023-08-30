package com.desafio.pitang.pitang.entity;


import com.desafio.pitang.pitang.model.UserCreateDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "table_user")
@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class UserPitang implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at",nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "update_at",nullable = false)
    @UpdateTimestamp
    private Date updateAt;

    private String fistName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private Date birthday;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    private String phone;

    private Date lastLogin;

    @OneToMany(mappedBy = "userPitangId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Car> cars = new HashSet<>();

    public UserPitang toEntity(UserCreateDto userDto){
        UserPitang userPitang = new UserPitang();
        userPitang.setFistName(userDto.getFistName());
        userPitang.setLastName(userDto.getLastName());
        userPitang.setEmail(userDto.getEmail());
        userPitang.setBirthday(userDto.getBirthday());
        userPitang.setLogin(userDto.getLogin());
        userPitang.setPassword(userDto.getPassword());
        userPitang.setPhone(userDto.getPhone());


        return userPitang;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
