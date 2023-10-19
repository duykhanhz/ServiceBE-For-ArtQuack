package com.duykhanhz.model.dto;

import lombok.Data;

@Data
public class AuthenResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
