package com.example.demo.service;

import com.example.demo.domain.dao.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface UserService {

    // zawsze stara sie dodac do cachea
    @CachePut(cacheNames = "user", key = "#result.id")
    User create(User user);
    User findByUsername(String username);

    List<User> getAll();
    // jesli w cache-u nie ma usera o podanym id to wywoluje funkcje getById w przeciwnym wypadku
    // w przeciwnym wypadku pobiera obiekt z cache-a
    @Cacheable(cacheNames = "user", key = "#id")
    User getById(Long id);
    @CachePut(cacheNames = "user", key = "#result.id")
    User update(User User, Long id);
    @CacheEvict(cacheNames = "user", key = "#id")
    void deleteById(Long id);
}
