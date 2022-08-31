package io.davidabejirin.dailyplanner.service;

import io.davidabejirin.dailyplanner.dto.UserDto;
import io.davidabejirin.dailyplanner.entity.User;
import io.davidabejirin.dailyplanner.exception.SignUpAndLoginException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(UserDto userDto) throws SignUpAndLoginException;
    User login(String email, String password) throws SignUpAndLoginException;
}
