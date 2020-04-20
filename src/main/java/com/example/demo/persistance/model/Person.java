package com.example.demo.persistance.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String sex;

    @Column
    private Date birtdate;

    @Column
    private String phoneNumber;
}
