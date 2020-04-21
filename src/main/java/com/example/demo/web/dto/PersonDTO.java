package com.example.demo.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String sex;

    private Date birtdate;

    private String phoneNumber;
}
