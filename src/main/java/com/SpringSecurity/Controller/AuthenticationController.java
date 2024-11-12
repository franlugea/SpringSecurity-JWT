package com.SpringSecurity.Controller;

import com.SpringSecurity.Controller.DTO.AuthCreateUser;
import com.SpringSecurity.Controller.DTO.AuthLogin;
import com.SpringSecurity.Controller.DTO.AuthResponse;
import com.SpringSecurity.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register (@RequestBody @Valid AuthCreateUser authCreateUser){
        return new ResponseEntity<>(authenticationService.createUser(authCreateUser),HttpStatus.CREATED);
    }


    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLogin userRequest){
        return new ResponseEntity<>(authenticationService.loginUser(userRequest),HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse>  refreshToken(HttpServletRequest request){
        return new ResponseEntity<>(authenticationService.refreshAccessToken(request),HttpStatus.OK);
    }




}
