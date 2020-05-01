package com.example.demo.service;

import com.example.demo.domain.dao.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User findByUsername(String username);

    List<User> getAll();
    User getById(Long id);
    User update(User User, Long id);
    void deleteById(Long id);
}
