package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.UserResponseDto;
import com.example.schedulemanagement.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserRepository {


    User findUserById(Long id);
}
