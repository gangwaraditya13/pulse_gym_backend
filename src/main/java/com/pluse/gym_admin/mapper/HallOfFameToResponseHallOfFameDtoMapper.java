package com.pluse.gym_admin.mapper;

import com.pluse.gym_admin.Dto.HallOfFameDto.ResponseHallOfFameDto;
import com.pluse.gym_admin.entity.HallOfFame;

public class HallOfFameToResponseHallOfFameDtoMapper {

    public static ResponseHallOfFameDto responseHallOfFameDto(HallOfFame hallOfFame){
        ResponseHallOfFameDto hallOfFameDto = new ResponseHallOfFameDto();

        hallOfFameDto.setId(hallOfFame.getId());
        hallOfFameDto.setChampionship(hallOfFame.getChampionship());
        hallOfFameDto.setChampionName(hallOfFame.getChampionName());
        hallOfFameDto.setImageUrl(hallOfFame.getImageUrl());
        hallOfFameDto.setPublicId(hallOfFame.getPublicId());

        return hallOfFameDto;
    }

}
