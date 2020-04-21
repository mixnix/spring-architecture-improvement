package com.example.demo.persistance.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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

    public Person(String firstName, String lastName, String sex, Date birtdate, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birtdate = birtdate;
        this.phoneNumber = phoneNumber;
    }
}
