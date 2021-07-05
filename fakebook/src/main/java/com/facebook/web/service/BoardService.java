package com.facebook.web.service;

import com.facebook.web.dto.BoardDTO;
import com.facebook.web.dto.PageRequestDTO;
import com.facebook.web.dto.PageResultDTO;
import com.facebook.web.entity.BoardEntity;

public interface BoardService {
    int modify(BoardDTO boardDTO);

    void remove(int bno);

    BoardDTO getOne(int bno);

    PageResultDTO getList(PageRequestDTO pageRequestDTO);
    int register(BoardDTO boardDTO);

    default BoardEntity dtoToEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = BoardEntity.builder()
                .bno(boardDTO.getBno())
                .bwriter(boardDTO.getBwriter())
                .bwriter_profile(boardDTO.getBwriter_profile())
                .btitle(boardDTO.getBtitle())
                //.bregdate(boardDTO.getBregdate)  삭제
                .image(boardDTO.getImage())
                .bcontent(boardDTO.getBcontent())
                .bviewcnt(boardDTO.getBviewcnt())
                .build();
        return boardEntity;
    }

    default BoardDTO entityToDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(boardEntity.getBno())
                //.bregdate(boardEntity.getBregdate()) 삭제
                .regDate(boardEntity.getRegDate())
                .modDate(boardEntity.getModDate())
                .bwriter(boardEntity.getBwriter())
                .bwriter_profile(boardEntity.getBwriter_profile())
                .btitle(boardEntity.getBtitle())
                .image(boardEntity.getImage())
                .bcontent(boardEntity.getBcontent())
                .bviewcnt(boardEntity.getBviewcnt())
                .build();
        return boardDTO;
    }

}
