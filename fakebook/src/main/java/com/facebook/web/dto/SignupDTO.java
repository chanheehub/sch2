package com.facebook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {
    String email;
    String password;
    String name;
    String gender;
    String birth;
}
