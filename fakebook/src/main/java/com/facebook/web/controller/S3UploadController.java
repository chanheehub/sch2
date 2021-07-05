package com.facebook.web.controller;


import com.facebook.web.dto.BoardDTO;
import com.facebook.web.dto.UploadResultDTO;
import com.facebook.web.dto.UserDTO;
import com.facebook.web.service.BoardService;
import com.facebook.web.service.S3Service;
import com.facebook.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class S3UploadController {
    @Autowired
    S3Service s3Service;

    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    @PostMapping("/uploadPost")
    public ResponseEntity<List<UploadResultDTO>> uploadPost(MultipartFile[] uploadFiles, BoardDTO boardDTO){

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile: uploadFiles) {

            if(uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            //실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);

            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파일 이름 중간에 "_"를 이용해서 구분
            String saveName = uuid +"_" + fileName;

            try {
                //원본 파일 저장
                s3Service.upload(uploadFile, saveName);


                resultDTOList.add(new UploadResultDTO(fileName,uuid,"static"));
                boardDTO.setImage(saveName);
                log.error("boardDTO =============== " + boardDTO.toString());
                boardService.register(boardDTO);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }//end for



        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }


    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(@AuthenticationPrincipal UserDTO userDTO, MultipartFile[] uploadFiles){

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile: uploadFiles) {

            if(uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            //실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);

            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파일 이름 중간에 "_"를 이용해서 구분
            String saveName = uuid +"_" + fileName;

            try {
                //원본 파일 저장
                s3Service.upload(uploadFile, saveName);


                resultDTOList.add(new UploadResultDTO(fileName,uuid,"static"));
                userDTO.setProfile_image(saveName);
                log.error("userDTO =============== " + userDTO.toString());
                userService.modify(userDTO);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }//end for
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }


}
