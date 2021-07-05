package com.facebook.web.service;

import com.facebook.web.dto.BoardDTO;
import com.facebook.web.dto.PageRequestDTO;
import com.facebook.web.dto.PageResultDTO;
import com.facebook.web.entity.BoardEntity;
import com.facebook.web.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Override
    public int modify(BoardDTO boardDTO) {
        BoardEntity result = boardRepository.save(dtoToEntity(boardDTO));

        return result.getBno();
    }

    @Override
    public void remove(int bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public BoardDTO getOne(int bno) {
        Optional<BoardEntity> result = boardRepository.findById(bno);

        if(result.isPresent()) {
            return entityToDTO(result.get());
        } else {
            return null;
        }
    }

    @Override
    public int register(BoardDTO boardDTO) {
        BoardEntity boardEntity = dtoToEntity(boardDTO);
        boardRepository.save(boardEntity);
        return boardEntity.getBno();
    }

    @Override
    public PageResultDTO getList(PageRequestDTO pageRequestDTO) {
        Sort sort = Sort.by("bno").descending();
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1,pageRequestDTO.getSize(), sort);
        Page<BoardEntity> result = boardRepository.findAll(pageable);

        List<BoardDTO> list = result.stream().map(
                boardEntity -> entityToDTO(boardEntity)
        ).collect(Collectors.toList());

        PageResultDTO pageResultDTO = new PageResultDTO();

        pageResultDTO.setBoardDTOList(list);
        pageResultDTO.setPage(result.getNumber()+1);
        pageResultDTO.setPrev(result.hasPrevious());
        pageResultDTO.setNext(result.hasNext());
        pageResultDTO.setTotalPage(result.getTotalPages());
        pageResultDTO.setSize(result.getSize());

        int tempEnd = (int)(Math.ceil((pageable.getPageNumber() + 1)/10.0)) * 10;
        pageResultDTO.setStart(tempEnd - 9);
        pageResultDTO.setEnd(
                result.getTotalPages() > tempEnd ?
                        tempEnd: result.getTotalPages()
        );
        pageResultDTO.setPageList(
                IntStream.rangeClosed(
                        pageResultDTO.getStart(),
                        pageResultDTO.getEnd()
                ).boxed().collect(Collectors.toList()));



        return pageResultDTO;
    }
}
