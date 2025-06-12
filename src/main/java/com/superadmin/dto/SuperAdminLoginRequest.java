package com.superadmin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SuperAdminLoginRequest {
    private String email;
    private String password;

}
