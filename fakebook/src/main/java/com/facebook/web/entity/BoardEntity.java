package com.facebook.web.entity;

import com.facebook.web.dto.UserDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bno;
    String btitle;
    String  bcontent;
    String  bwriter;
    String  bwriter_profile;
    int bviewcnt;
    // Date bregdate; 삭제
    String image;

    @CreationTimestamp
    @Column(name = "regdate" , updatable = false)
    private Timestamp regDate;

    @LastModifiedDate
    @Column(name ="moddate" )
    private Timestamp  modDate;
}
