package com.example.contabliumv2.Service.UserService;

import com.example.contabliumv2.Dto.UserDto;
import com.example.contabliumv2.Model.User;
import jakarta.jws.soap.SOAPBinding;

public interface UserService {

    User findByUsername(String username);
    User save(UserDto userDto);


}
