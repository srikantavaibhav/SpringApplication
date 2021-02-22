package com.example.springsession.service;

import com.example.springsession.dto.MyRequestDTO;

public interface UserService {
    boolean updateEmployee(MyRequestDTO request, String id);
}
