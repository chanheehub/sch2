package com.facebook.web.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name="user")
public class UserEntity {
    @Id
    String email;

    @Column(updatable=false)
    String password;
    String name;
    String gender;
    String birth;
    String profile_image;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    Set<UserRole> roleSet = new HashSet<>();

    public void addUserRole(UserRole userRole) {
        roleSet.add(userRole);
    }
}
