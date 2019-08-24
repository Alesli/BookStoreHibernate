package com.project.service;

import com.project.entity.User;

public interface UserService {

    User findOneById(Long id);

    User findOneByName(String name);

    boolean isCashEnough(Long id, Double cash);

    User updateCash(Long id, Double cash);

    User save(User user);
}