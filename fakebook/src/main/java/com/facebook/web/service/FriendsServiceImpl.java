package com.facebook.web.service;

import com.facebook.web.dto.FriendsDTO;

import com.facebook.web.entity.FriendsEntity;
import com.facebook.web.entity.FriendsId;
import com.facebook.web.repository.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendsServiceImpl implements FriendsService {
    @Autowired
    FriendsRepository friendsRepository;

    @Override
    public List<FriendsDTO> findAllMe(String me) {
        List<FriendsEntity> result = friendsRepository.findAll();

        if(!result.isEmpty()){
            for(int i=0; i<result.size(); i++) {
                System.out.println("index number~~~~~"+i);
                System.out.println("FriendsEntity is~~~~~"+result.get(i).getFriendsId().toString());
                if (!result.get(i).getFriendsId().getMe().equals(me)){
                    result.remove(i);
                    i--;
                }
            }

            for (FriendsEntity friendsEntity:result) {

            }
        }


        List<FriendsDTO> list = result.stream().map(
                friendsEntity -> entityToDTO(friendsEntity)
        ).collect(Collectors.toList());

        return list;
    }

    @Override
    public FriendsId register(FriendsDTO friendsDTO) {
        FriendsEntity friendsEntity = dtoToEntity(friendsDTO);
        friendsRepository.save(friendsEntity);
        return friendsEntity.getFriendsId();
    }


}
