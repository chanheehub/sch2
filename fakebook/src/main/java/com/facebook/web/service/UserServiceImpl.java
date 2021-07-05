package com.facebook.web.service;

import com.facebook.web.dto.SignupDTO;
import com.facebook.web.dto.UserDTO;
import com.facebook.web.entity.BoardEntity;
import com.facebook.web.entity.UserEntity;
import com.facebook.web.entity.UserRole;
import com.facebook.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public void modify(UserDTO userDTO) {
        UserEntity result = userRepository.save(dtoToEntity(userDTO));
    }



    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<UserEntity> result = userRepository.findByEmail(username);
        UserEntity userEntity = result.get();
        System.out.println("loadUserByUsername : " + userEntity.toString());
        UserDTO userDTO = new UserDTO(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getProfile_image(),
                userEntity.getGender(),
                userEntity.getBirth(),
                userEntity.getRoleSet()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(
                                "ROLE_"+role.name()
                        )).collect(Collectors.toSet())
        );

        userDTO.setName(userEntity.getName());

        return userDTO;
    }

    @Override
    public void signup(SignupDTO signupDTO){
        UserEntity userEntity = UserEntity.builder()
                .email(signupDTO.getEmail())
                .name(signupDTO.getName())
                .gender(signupDTO.getGender())
                .birth(signupDTO.getBirth())
                .profile_image("nobody.png")
                .password(passwordEncoder.encode(signupDTO.getPassword()))
                .build();

        //userEntity.addUserRole(UserRole.USER);

        userRepository.save(userEntity);
    }
}
