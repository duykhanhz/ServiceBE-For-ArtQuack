package com.duykhanhz.model.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String id;
    private String username;
    private String password;
    private String email;
    private String userRoleId;
    private boolean status;
}
