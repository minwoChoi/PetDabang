package com.example.demo.service;

import com.example.demo.entity.UserBoard;
import com.example.demo.exception.DuplicateIdOrUsernameException;

public interface UserService {
    void write(UserBoard userBoard) throws DuplicateIdOrUsernameException;

    boolean authenticateUser(int ID, String PW);

    String getUsernameById(int id);
}
