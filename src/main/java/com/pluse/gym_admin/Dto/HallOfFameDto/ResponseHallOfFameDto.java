package com.pluse.gym_admin.Dto.HallOfFameDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHallOfFameDto {
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("_id")
    private ObjectId id;
    @NonNull
    private String championName;
    @NonNull
    private String championship;

    private String ImageUrl;

    private String publicId;
}
