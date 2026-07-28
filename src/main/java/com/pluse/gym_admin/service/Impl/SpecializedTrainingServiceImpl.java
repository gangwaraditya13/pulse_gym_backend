package com.pluse.gym_admin.service.Impl;

import com.pluse.gym_admin.Dto.SpecializedTrainingDto.RequestSpecializedTrainingDto;
import com.pluse.gym_admin.Dto.SpecializedTrainingDto.ResponseSpecializedTrainingDto;
import com.pluse.gym_admin.entity.SpecializedTraining;
import com.pluse.gym_admin.mapper.SpecializedTrainingToResponseSpecializedTrainingMapper;
import com.pluse.gym_admin.repository.SpecializedTrainingRepository;
import com.pluse.gym_admin.service.SpecializedTrainingService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class SpecializedTrainingServiceImpl implements SpecializedTrainingService {
    @Autowired
    private SpecializedTrainingRepository specializedTrainingRepository;

    @Override
    public ResponseSpecializedTrainingDto createSpecializedTraining(RequestSpecializedTrainingDto requestSpecializedTrainingDto) {

        try {
            SpecializedTraining specializedTraining = new SpecializedTraining();

            specializedTraining.setProgramName(requestSpecializedTrainingDto.getProgramName());
            specializedTraining.setProgramDescription(requestSpecializedTrainingDto.getProgramDescription());
            specializedTraining.setCreateAt(LocalDateTime.now());
            specializedTraining.setUpdateAt(LocalDateTime.now());

            SpecializedTraining save = specializedTrainingRepository.save(specializedTraining);

            ResponseSpecializedTrainingDto responseSpecializedTrainingDto = SpecializedTrainingToResponseSpecializedTrainingMapper.responseSpecializedTrainingDto(save);

            return responseSpecializedTrainingDto;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ResponseSpecializedTrainingDto> getAllSpecializedTraining() {

        List<SpecializedTraining> specializedTrainingList = specializedTrainingRepository.findAll();

        List<ResponseSpecializedTrainingDto> responseSpecializedTrainingDtoList = specializedTrainingList
                .stream()
                .map(SpecializedTrainingToResponseSpecializedTrainingMapper::responseSpecializedTrainingDto)
                .toList();

        return responseSpecializedTrainingDtoList;
    }

    @Override
    public ResponseSpecializedTrainingDto updateSpecializedTraining(RequestSpecializedTrainingDto requestSpecializedTrainingDto, ObjectId id) {

        SpecializedTraining specializedTraining = specializedTrainingRepository.findById(id).orElseThrow();

        boolean isUpdate = false;

        if(requestSpecializedTrainingDto.getProgramName() != null
                && !requestSpecializedTrainingDto.getProgramName().trim().equals("")
                && !requestSpecializedTrainingDto.getProgramName().trim().equals(specializedTraining.getProgramName())){
            specializedTraining.setProgramName(requestSpecializedTrainingDto.getProgramName());
            specializedTraining.setUpdateAt(LocalDateTime.now());
            isUpdate = true;
        }
        if(requestSpecializedTrainingDto.getProgramDescription() != null
                && !requestSpecializedTrainingDto.getProgramDescription().trim().equals("")
                && !requestSpecializedTrainingDto.getProgramDescription().trim().equals(specializedTraining.getProgramDescription())){
            specializedTraining.setProgramDescription(requestSpecializedTrainingDto.getProgramDescription());
            specializedTraining.setUpdateAt(LocalDateTime.now());
            isUpdate = true;
        }

        if(isUpdate){
            SpecializedTraining saved = specializedTrainingRepository.save(specializedTraining);

            ResponseSpecializedTrainingDto responseSpecializedTrainingDto = SpecializedTrainingToResponseSpecializedTrainingMapper.responseSpecializedTrainingDto(saved);
            return responseSpecializedTrainingDto;

        }

        return null;
    }

    @Override
    public boolean deleteSpecializedTraining(ObjectId id) {

        try{
            specializedTrainingRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }

    }
}
