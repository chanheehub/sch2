package com.facebook.web.service;

import com.facebook.web.dto.FriendsDTO;
import com.facebook.web.entity.FriendsEntity;
import com.facebook.web.entity.FriendsId;


import java.util.List;

public interface FriendsService {
    List<FriendsDTO> findAllMe(String me);

    FriendsId register(FriendsDTO FriendsDTO);

    default FriendsEntity dtoToEntity(FriendsDTO FriendsDTO){
        FriendsId friendsId = new FriendsId();
        friendsId.setMy(FriendsDTO.getMy());
        friendsId.setMe(FriendsDTO.getMe());
        FriendsEntity FriendsEntity = com.facebook.web.entity.FriendsEntity.builder()
                .friendsId(friendsId)
                .my_profile(FriendsDTO.getMy_profile())
                .build();
        return FriendsEntity;
    }

    default FriendsDTO entityToDTO(FriendsEntity FriendsEntity){
        FriendsDTO FriendsDTO = com.facebook.web.dto.FriendsDTO.builder()
                .my(FriendsEntity.getFriendsId().getMy())
                .me(FriendsEntity.getFriendsId().getMe())
                .my_profile(FriendsEntity.getMy_profile())
                .build();
        return FriendsDTO;
    }

}
