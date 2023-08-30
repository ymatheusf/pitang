package com.desafio.pitang.pitang.model;

import com.desafio.pitang.pitang.entity.UserPitang;

public class UserUpdateDto {
    private String fistName;
    private String lastName;

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin() {
        this.login = login;
    }

    private String phone;

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

    public String getPassword() {
        return password;
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

    public UserPitang toEntity(UserPitang userPitang, UserUpdateDto userDto){
        userPitang.setFistName(userDto.getFistName());
        userPitang.setLastName(userDto.getLastName());
        userPitang.setLogin(userDto.getLogin());
        userPitang.setPassword(userDto.getPassword());
        userPitang.setPhone(userDto.getPhone());

        return userPitang;
    }
}
