package com.pluse.gym_admin.service;

import com.pluse.gym_admin.Dto.SpecializedTrainingDto.RequestSpecializedTrainingDto;
import com.pluse.gym_admin.Dto.SpecializedTrainingDto.ResponseSpecializedTrainingDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface SpecializedTrainingService {

    ResponseSpecializedTrainingDto createSpecializedTraining(RequestSpecializedTrainingDto requestSpecializedTrainingDto);

    List<ResponseSpecializedTrainingDto> getAllSpecializedTraining();

    ResponseSpecializedTrainingDto updateSpecializedTraining(RequestSpecializedTrainingDto requestSpecializedTrainingDto, ObjectId id);

    boolean deleteSpecializedTraining(ObjectId id);

}
