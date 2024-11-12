package com.SpringSecurity.Controller.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthLogin(
       @NotBlank String username,
       @NotBlank String password
) {
}
