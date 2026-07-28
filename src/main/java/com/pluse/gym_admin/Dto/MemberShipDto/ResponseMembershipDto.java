package com.pluse.gym_admin.Dto.MemberShipDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMembershipDto {

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("_id")
    private ObjectId id;
    @NonNull
    private String planName;
    @NonNull
    private String planDuration;
    @NonNull
    private int planPrice;

    private int discount;
}
