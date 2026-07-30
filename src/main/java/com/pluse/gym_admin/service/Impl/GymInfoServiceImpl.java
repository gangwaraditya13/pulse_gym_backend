package com.pluse.gym_admin.service.Impl;

import com.pluse.gym_admin.entity.GymInfo;
import com.pluse.gym_admin.repository.GymInfoRepository;
import com.pluse.gym_admin.service.GymInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymInfoServiceImpl implements GymInfoService {

    @Autowired
    private GymInfoRepository gymInfoRepository;

    @Override
    public List<GymInfo> getInfo() {

        List<GymInfo> all = gymInfoRepository.findAll();

        return all;
    }
}
