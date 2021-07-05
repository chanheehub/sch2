package com.facebook.web.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="friends")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FriendsEntity {
    @EmbeddedId
    FriendsId friendsId;
    String  my_profile;
}
