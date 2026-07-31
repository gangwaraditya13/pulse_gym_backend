package com.pluse.gym_admin.service.Impl;

import com.pluse.gym_admin.Dto.HallOfFameDto.RequestHallOfFameDto;
import com.pluse.gym_admin.Dto.HallOfFameDto.ResponseHallOfFameDto;
import com.pluse.gym_admin.entity.HallOfFame;
import com.pluse.gym_admin.mapper.HallOfFameToResponseHallOfFameDtoMapper;
import com.pluse.gym_admin.repository.HallOfFameRepository;
import com.pluse.gym_admin.service.HallOfFameService;
import com.pluse.gym_admin.service.Impl.Cloudinary.CloudinaryImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class HallOfFameServiceImpl implements HallOfFameService {

    @Autowired
    private HallOfFameRepository hallOfFameRepository;

    @Autowired
    private CloudinaryImpl cloudinary;

    @Override
    public ResponseHallOfFameDto createHallOfFame(RequestHallOfFameDto hallOfFameDto) {

        try{

            HallOfFame hallOfFame = new HallOfFame();

            hallOfFame.setChampionName(hallOfFameDto.getChampionName());
            hallOfFame.setChampionName(hallOfFameDto.getChampionName());
            hallOfFame.setImageUrl(hallOfFameDto.getImageUrl());
            hallOfFame.setPublicId(hallOfFameDto.getPublicId());
            hallOfFame.setCreatedAt(LocalDateTime.now());
            hallOfFame.setUpdatedAt(LocalDateTime.now());

            HallOfFame saved = hallOfFameRepository.save(hallOfFame);

            ResponseHallOfFameDto hallOfFameDto1 = HallOfFameToResponseHallOfFameDtoMapper.responseHallOfFameDto(saved);

            return hallOfFameDto1;

        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseHallOfFameDto updateHallOfFame(RequestHallOfFameDto hallOfFameDto, ObjectId id) throws IOException {

        HallOfFame hallOfFame = hallOfFameRepository.findById(id).orElseThrow();

        boolean isUpdate = false;

        if(hallOfFameDto.getChampionName() != null
                && !hallOfFameDto.getChampionName().trim().equals("")
                && !hallOfFameDto.getChampionName().trim().equals(hallOfFame.getChampionName())){
            hallOfFame.setChampionName(hallOfFameDto.getChampionName());
            hallOfFame.setUpdatedAt(LocalDateTime.now());
            isUpdate=true;
        }
        if(hallOfFameDto.getChampionship() != null
                && !hallOfFameDto.getChampionship().trim().equals("")
                && !hallOfFameDto.getChampionship().trim().equals(hallOfFame.getChampionship())){
            hallOfFame.setChampionship(hallOfFameDto.getChampionship());
            hallOfFame.setUpdatedAt(LocalDateTime.now());
            isUpdate=true;
        }
        if(hallOfFameDto.getImageUrl() != null
                && !hallOfFameDto.getImageUrl().trim().equals("")
                && !hallOfFameDto.getImageUrl().trim().equals(hallOfFame.getImageUrl())){
            hallOfFame.setImageUrl(hallOfFameDto.getImageUrl());
            hallOfFame.setUpdatedAt(LocalDateTime.now());
            isUpdate=true;
        }
        if(hallOfFameDto.getPublicId() != null
                && !hallOfFameDto.getPublicId().trim().equals("")
                && !hallOfFameDto.getPublicId().trim().equals(hallOfFame.getPublicId())){
            //delete image from cloudinary
            if(hallOfFameDto.getPublicId() != null
                    && !hallOfFameDto.getPublicId().trim().equals("")) {
                cloudinary.deleteImage(hallOfFame.getPublicId());
            }
            hallOfFame.setChampionName(hallOfFameDto.getPublicId());
            hallOfFame.setUpdatedAt(LocalDateTime.now());
            isUpdate=true;
        }

        if(isUpdate){
            HallOfFame saved = hallOfFameRepository.save(hallOfFame);
            ResponseHallOfFameDto hallOfFameDto1 = HallOfFameToResponseHallOfFameDtoMapper.responseHallOfFameDto(saved);
            return hallOfFameDto1;
        }

        return HallOfFameToResponseHallOfFameDtoMapper.responseHallOfFameDto(hallOfFame);
    }

    @Override
    public List<ResponseHallOfFameDto> getAllHallOfFame() {

        List<HallOfFame> hallOfFameList = hallOfFameRepository.findAll();

        List<ResponseHallOfFameDto> responseHallOfFameDtoList = hallOfFameList.stream().map(HallOfFameToResponseHallOfFameDtoMapper::responseHallOfFameDto).toList();

        return responseHallOfFameDtoList;
    }

    @Override
    @Transactional
    public boolean deleteHallOfFame(ObjectId id) {
        try {
            HallOfFame hallOfFame = hallOfFameRepository.findById(id).orElseThrow();
            //delete image from cloudinary
            if(hallOfFame.getPublicId() != null
                    && !hallOfFame.getPublicId().trim().equals("")) {
                cloudinary.deleteImage(hallOfFame.getPublicId());
            }
            hallOfFameRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
