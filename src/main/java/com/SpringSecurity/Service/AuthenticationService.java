package com.SpringSecurity.Service;

import com.SpringSecurity.Controller.DTO.AuthCreateUser;
import com.SpringSecurity.Controller.DTO.AuthLogin;
import com.SpringSecurity.Controller.DTO.AuthResponse;
import com.SpringSecurity.Entity.RolesEntity;
import com.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.Repository.RoleRepository;
import com.SpringSecurity.Repository.UserRepository;
import com.SpringSecurity.Security.JWT.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    @Autowired
    UserRepository repository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    MyUserDatailsService myUserDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;

    public AuthResponse loginUser(AuthLogin userRequest) {
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.username(), userRequest.password()));
            String accessToken= jwtUtil.createToken(authentication);
            String refreshToken= jwtUtil.createRefreshToken(authentication);
            return new AuthResponse(accessToken, refreshToken);
    }



    public AuthResponse createUser (AuthCreateUser authCreateUser) {
        if(repository.existsUsuarioEntityByName(authCreateUser.username())){
            throw new  IllegalArgumentException("El usuario ya existe");
        }

        Set<RolesEntity> rolesEntitySet = this.getRolesEntitySet(authCreateUser.roleRequest().roleListname());

        UserEntity userEntity =this.buildUsuarioEntity(authCreateUser,rolesEntitySet);
        repository.save(userEntity);
        Authentication authentication= this.createAuthentication(userEntity);
        String accessToken=jwtUtil.createToken(authentication);
        String refreshToken= jwtUtil.createRefreshToken(authentication);

       return new AuthResponse(accessToken, refreshToken);

    }

    public AuthResponse refreshAccessToken(HttpServletRequest request){

        String refreshToken= request.getHeader(HttpHeaders.AUTHORIZATION);
        refreshToken=refreshToken.substring(7);
        DecodedJWT decodedJWT= jwtUtil.validateToken(refreshToken);
        String username= jwtUtil.extractUsername(decodedJWT);
        UserDetails userDetails=this.myUserDetailsService.loadUserByUsername(username);
        String newAccessToken=this.jwtUtil.createRefreshToken(new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities()));

        return new AuthResponse(newAccessToken,refreshToken);
    }

    private Set<RolesEntity> getRolesEntitySet(List<String> roleRequest) {
        Set<RolesEntity> rolesEntitySet = roleRepository.findRolesEntitiesByRoleIn(roleRequest)
                .stream()
                .collect(Collectors.toSet());

        if (rolesEntitySet.isEmpty()) {
            throw new IllegalArgumentException("Los roles no existen");
        }
        return rolesEntitySet;
    }

    private UserEntity buildUsuarioEntity(AuthCreateUser authCreateUser, Set<RolesEntity> rolesEntitySet) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(authCreateUser.username());
        userEntity.setPassword(passwordEncoder.encode(authCreateUser.password()));
        userEntity.setRoles(rolesEntitySet);
        userEntity.setEnabled(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setCredentialsNonExpired(true);
        return userEntity;
    }

    private Authentication createAuthentication(UserEntity userCreated) {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(role ->
                authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
        );

        userCreated.getRoles().stream()
                .flatMap(role -> role.getPermisosList().stream())
                .forEach(permission ->
                        authorityList.add(new SimpleGrantedAuthority(permission.getName()))
                );

        return new UsernamePasswordAuthenticationToken(userCreated.getName(), null, authorityList);
    }




}
