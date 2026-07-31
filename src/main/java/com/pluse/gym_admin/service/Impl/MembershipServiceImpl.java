package com.pluse.gym_admin.service.Impl;

import com.pluse.gym_admin.Dto.MemberShipDto.RequestMembershipDto;
import com.pluse.gym_admin.Dto.MemberShipDto.ResponseMembershipDto;
import com.pluse.gym_admin.entity.Membership;
import com.pluse.gym_admin.mapper.MembershipToResponseMembershipMapper;
import com.pluse.gym_admin.repository.MembershipRepository;
import com.pluse.gym_admin.service.MembershipService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public ResponseMembershipDto createMembership(RequestMembershipDto requestMembershipDto) {

        try {

            Membership membership = new Membership();

            membership.setPlanName(requestMembershipDto.getPlanName());
            membership.setPlanDuration(requestMembershipDto.getPlanDuration());
            membership.setPlanPrice(requestMembershipDto.getPlanPrice());
            membership.setDiscount(requestMembershipDto.getDiscount());
            membership.setCreatedAt(LocalDateTime.now());
            membership.setUpdatedAt(LocalDateTime.now());

            Membership saved = membershipRepository.save(membership);

            ResponseMembershipDto responseMembershipDto = MembershipToResponseMembershipMapper.responseMembershipDto(saved);

            return responseMembershipDto;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ResponseMembershipDto> getAllMembership() {

        List<Membership> membershipList = membershipRepository.findAll();

        List<ResponseMembershipDto> responseMembershipDtoList = membershipList
                .stream()
                .map(MembershipToResponseMembershipMapper::responseMembershipDto)
                .collect(Collectors.toList());

        return responseMembershipDtoList;
    }



    @Override
    public ResponseMembershipDto updateMembership(RequestMembershipDto requestMembershipDto, ObjectId id) {

        Membership membership = membershipRepository.findById(id).orElseThrow();

        if(requestMembershipDto.getPlanName() != null
                && !requestMembershipDto.getPlanName().trim().equals("")
                && !membership.getPlanName().equals(requestMembershipDto.getPlanName().trim())){
            membership.setPlanName(requestMembershipDto.getPlanName());
            membership.setUpdatedAt(LocalDateTime.now());
        }

        if(requestMembershipDto.getPlanDuration() != null
                && !requestMembershipDto.getPlanDuration().trim().equals("")
                && !membership.getPlanDuration().equals(requestMembershipDto.getPlanDuration().trim())){
            membership.setPlanDuration(requestMembershipDto.getPlanDuration());
            membership.setUpdatedAt(LocalDateTime.now());
        }

        if(membership.getPlanPrice() != requestMembershipDto.getPlanPrice()){
            membership.setPlanPrice(requestMembershipDto.getPlanPrice());
            membership.setUpdatedAt(LocalDateTime.now());
        }

        if(membership.getDiscount() != requestMembershipDto.getDiscount()){
            membership.setDiscount(requestMembershipDto.getDiscount());
            membership.setUpdatedAt(LocalDateTime.now());
        }

        Membership saved = membershipRepository.save(membership);
        ResponseMembershipDto responseMembershipDto = MembershipToResponseMembershipMapper.responseMembershipDto(saved);

        return responseMembershipDto;

    }

    @Override
    public boolean deleteMembershipById(ObjectId id) {

        try {
            membershipRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Membership plan not deleted : "+e.getMessage());
        }
    }
}
