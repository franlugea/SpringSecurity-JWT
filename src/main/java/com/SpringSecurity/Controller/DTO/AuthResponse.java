package com.SpringSecurity.Controller.DTO;

public record AuthResponse(
        String accesToken,
        String refreshToken
) {
}
