package com.example.contabliumv2.Service;


import com.example.contabliumv2.Model.User;
import com.example.contabliumv2.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Nombre de usuario o contraseña incorrecta");
        }
        return new CustomUserDetails(user.getUsername(),user.getPassword(),authorities());
    }

    public Collection<? extends GrantedAuthority> authorities(){

        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }
}
