package com.desafio.pitang.pitang.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Table(name = "table_car")
@Entity
@Setter
@Getter
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at",nullable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "update_at",nullable = false)
    @LastModifiedDate
    private Date updateAt;
    private Long ano;
    private String licensePlate;
    private String model;
    private String color;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "userPitangId")
    private UserPitang userPitangId;
}
