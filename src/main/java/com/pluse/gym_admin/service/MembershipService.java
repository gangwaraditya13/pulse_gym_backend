package com.pluse.gym_admin.service;

import com.pluse.gym_admin.Dto.MemberShipDto.RequestMembershipDto;
import com.pluse.gym_admin.Dto.MemberShipDto.ResponseMembershipDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface MembershipService {

    ResponseMembershipDto createMembership(RequestMembershipDto requestMembershipDto);

    List<ResponseMembershipDto> getAllMembership();

    ResponseMembershipDto updateMembership(RequestMembershipDto requestMembershipDto, ObjectId id);

    boolean deleteMembershipById(ObjectId id);

}
