package com.facebook.web.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Getter
@Setter
@Embeddable
public
class FriendsId implements Serializable {

    @Column(name = "me")
    private String me;

    @Column(name = "my")
    private String my;
}
