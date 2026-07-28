package com.pluse.gym_admin.Dto.ClientQueryMessageDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClientQueryMessageDto {
    @JsonProperty("_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @NonNull
    private String fullName;
    @NonNull
    private String email;
    @NonNull
    private String message;
}
