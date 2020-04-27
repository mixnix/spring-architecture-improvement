package com.example.demo.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person  {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String sex;

    private LocalDate birthDate;

    private String phoneNumber;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public Person(String firstName, String lastName, String sex, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
    }
}
