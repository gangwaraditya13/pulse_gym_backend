package com.pluse.gym_admin.Dto.MemberShipDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestMembershipDto {
    @NonNull
    private String planName;
    @NonNull
    private String planDuration;
    @NonNull
    private int planPrice;

    private int discount;
}
