package com.facebook.web.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO {
    List<BoardDTO> boardDTOList;
    int totalPage;
    int page;
    int size;
    boolean prev, next;
    int start, end;
    List<Integer> pageList;

}
