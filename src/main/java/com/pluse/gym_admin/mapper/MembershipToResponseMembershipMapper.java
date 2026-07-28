package com.pluse.gym_admin.mapper;

import com.pluse.gym_admin.Dto.MemberShipDto.ResponseMembershipDto;
import com.pluse.gym_admin.entity.Membership;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MembershipToResponseMembershipMapper {

    public static ResponseMembershipDto responseMembershipDto(Membership membership){

        ResponseMembershipDto membershipDto = new ResponseMembershipDto();

        membershipDto.setId(membership.getId());
        membershipDto.setPlanName(membership.getPlanName());
        membershipDto.setPlanDuration(membership.getPlanDuration());
        membershipDto.setPlanPrice(membership.getPlanPrice());
        membershipDto.setDiscount(membership.getDiscount());

        return membershipDto;
    }

}
