package com.facebook.web.service;

import com.facebook.web.dto.BoardDTO;
import com.facebook.web.dto.SignupDTO;
import com.facebook.web.dto.UserDTO;
import com.facebook.web.entity.BoardEntity;
import com.facebook.web.entity.UserEntity;

public interface UserService {
    public void signup(SignupDTO signupDTO);

    public void modify(UserDTO userDTO);

    default UserEntity dtoToEntity(UserDTO userDTO){
        UserEntity userEntity = UserEntity.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .gender(userDTO.getGender())
                .birth(userDTO.getBirth())
                .profile_image(userDTO.getProfile_image())
                .build();
        return userEntity;
    }


}
