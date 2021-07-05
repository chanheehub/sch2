package com.facebook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    int bno;
    String btitle;
    String  bcontent;
    String  bwriter;
    String  bwriter_profile;
    //Date bregdate;    삭제
    String image;
    Timestamp regDate;
    Timestamp modDate;
    int bviewcnt;
}
