package com.pluse.gym_admin.Dto.MemberShipDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestMembershipDto {
    @NotBlank
    private String planName;
    @NotBlank
    private String planDuration;
    @NotBlank
    private int planPrice;

    private int discount;
}
