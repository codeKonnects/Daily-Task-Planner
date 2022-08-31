package io.davidabejirin.dailyplanner.service.serviceImpl;

import io.davidabejirin.dailyplanner.dto.UserDto;
import io.davidabejirin.dailyplanner.entity.User;
import io.davidabejirin.dailyplanner.exception.SignUpAndLoginException;
import io.davidabejirin.dailyplanner.repository.UserRepository;
import io.davidabejirin.dailyplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public User createUser(UserDto userDto) throws SignUpAndLoginException {
        Optional<User> userFromDb = userRepository.findByEmail(userDto.getEmail());

        if (userFromDb.isPresent()) {
            throw new SignUpAndLoginException("User already exists.");
        }else {
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setLastName(userDto.getLastName());
            user.setFirstName(userDto.getFirstName());
            return userRepository.save(user);
        }
    }

    @Override
    public User login(String email, String password) throws SignUpAndLoginException {

        User user = userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new SignUpAndLoginException("Invalid email or password"));
        return user;
    }
}

