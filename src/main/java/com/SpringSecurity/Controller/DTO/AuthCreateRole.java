package com.SpringSecurity.Controller.DTO;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthCreateRole(
        @Size(max = 3, message = "No puede tner mas de 3 roles")
        List<String> roleListname) {
}
