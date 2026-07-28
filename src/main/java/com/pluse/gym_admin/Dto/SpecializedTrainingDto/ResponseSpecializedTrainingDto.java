package com.pluse.gym_admin.Dto.SpecializedTrainingDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSpecializedTrainingDto {
    @JsonProperty("_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @NonNull
    private String programName;
    @NonNull
    private String programDescription;

}
