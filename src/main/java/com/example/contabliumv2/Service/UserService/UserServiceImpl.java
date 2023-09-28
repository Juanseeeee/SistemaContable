package com.example.contabliumv2.Service.UserService;

import com.example.contabliumv2.Dto.UserDto;
import com.example.contabliumv2.Model.User;
import com.example.contabliumv2.Repository.UserRepository;
import com.example.contabliumv2.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()), userDto.getFullname());
        return userRepository.save(user);
    }
}
