package com.superadmin.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest {
    private String email;
    private String password;

}
