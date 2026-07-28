package com.pluse.gym_admin.service;

import com.pluse.gym_admin.Dto.HallOfFameDto.RequestHallOfFameDto;
import com.pluse.gym_admin.Dto.HallOfFameDto.ResponseHallOfFameDto;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.List;

public interface HallOfFameService {

    ResponseHallOfFameDto createHallOfFame(RequestHallOfFameDto hallOfFameDto);

    ResponseHallOfFameDto updateHallOfFame(RequestHallOfFameDto hallOfFameDto, ObjectId id) throws IOException;

    List<ResponseHallOfFameDto> getAllHallOfFame();

    boolean deleteHallOfFame(ObjectId id);

}
