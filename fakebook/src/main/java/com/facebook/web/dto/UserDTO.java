package com.facebook.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class UserDTO extends User {
    String email;
    String name;
    String password;
    String gender;
    String birth;
    String profile_image;

    public UserDTO(String username,
                   String password,
                   String name,
                   String profile_image,
                   String gender,
                   String birth,
                   Collection<? extends GrantedAuthority> authority) {
        super(username, password, authority);
        this.email = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.profile_image = profile_image;
        this.birth = birth;
    }
}
