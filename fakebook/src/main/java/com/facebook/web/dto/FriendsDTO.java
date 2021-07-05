package com.facebook.web.dto;

import com.facebook.web.entity.FriendsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendsDTO {
    String my;
    String me;
    String  my_profile;
}
