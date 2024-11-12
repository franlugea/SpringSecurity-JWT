package com.SpringSecurity.Service;


import com.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDatailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario"+username+" no existe"));

        List<SimpleGrantedAuthority> authorityList= new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermisosList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(userEntity.getName(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNonExpired(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isAccountNonLocked(),
                authorityList);
    }

}
