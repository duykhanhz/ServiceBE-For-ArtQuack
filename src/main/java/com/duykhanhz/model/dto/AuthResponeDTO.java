package com.duykhanhz.model.dto;

import lombok.Data;

@Data
public class AuthResponeDTO {
    private String accessToken;
    private String tokenType ="Bearer";

    public AuthResponeDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
