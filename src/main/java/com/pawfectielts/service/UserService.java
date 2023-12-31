package com.pawfectielts.service;

import com.pawfectielts.entity.User;

public interface UserService {
    User getUserById(Long userId);
    User validateLogin(String username, String password);
}
