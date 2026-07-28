package com.pluse.gym_admin.mapper;

import com.pluse.gym_admin.Dto.SpecializedTrainingDto.ResponseSpecializedTrainingDto;
import com.pluse.gym_admin.entity.SpecializedTraining;

public class SpecializedTrainingToResponseSpecializedTrainingMapper {
    public static ResponseSpecializedTrainingDto responseSpecializedTrainingDto(SpecializedTraining specializedTraining){
        ResponseSpecializedTrainingDto responseSpecializedTrainingDto = new ResponseSpecializedTrainingDto();

        responseSpecializedTrainingDto.setId(specializedTraining.getId());
        responseSpecializedTrainingDto.setProgramName(specializedTraining.getProgramName());
        responseSpecializedTrainingDto.setProgramDescription(specializedTraining.getProgramDescription());

        return responseSpecializedTrainingDto;
    }

}
