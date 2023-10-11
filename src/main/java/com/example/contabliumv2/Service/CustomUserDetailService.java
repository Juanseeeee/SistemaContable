package com.example.contabliumv2.Service;


import com.example.contabliumv2.Model.User;
import com.example.contabliumv2.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Nombre de usuario o contraseña incorrecta");
        }
        return new CustomUserDetails(user.getUsername(),user.getPassword(),authorities(user),user.getFullname());
    }

    private Collection<? extends GrantedAuthority> authorities(User user) {
        String role = String.valueOf(user.getRole());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER")); // Todos los usuarios tienen el rol USER por defecto
        if ("ADMIN".equals(role)) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        System.out.println("Authorities for user " + user.getUsername() + ": " + authorities);
        return authorities;
    }
}

