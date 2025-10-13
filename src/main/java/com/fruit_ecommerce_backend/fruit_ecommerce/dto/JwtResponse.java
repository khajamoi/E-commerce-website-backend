package com.fruit_ecommerce_backend.fruit_ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String email;
    private String role;
}
