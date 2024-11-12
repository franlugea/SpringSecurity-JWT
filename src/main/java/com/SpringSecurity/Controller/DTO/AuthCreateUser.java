package com.SpringSecurity.Controller.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUser(
        @NotBlank String username,
        @NotBlank String password,
        @Valid AuthCreateRole roleRequest) {
}
