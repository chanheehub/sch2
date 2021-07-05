package com.facebook.web.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
public class BaseEntity {
    @CreationTimestamp
    @Column(name = "regdate" , updatable = false)
    private Timestamp regDate;

    @LastModifiedDate
    @Column(name ="moddate" )
    private Timestamp  modDate;
}
