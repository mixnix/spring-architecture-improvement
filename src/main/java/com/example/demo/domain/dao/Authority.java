package com.example.demo.domain.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity

@Getter
@Setter

@RequiredArgsConstructor

public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    Set<User> students;

    @Override
    public String getAuthority() {
        return name;
    }
    public Authority(String name) {
        this.name = name;
    }
}
