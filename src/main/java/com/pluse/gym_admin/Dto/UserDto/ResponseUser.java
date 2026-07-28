package com.pluse.gym_admin.Dto.UserDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("_id")
    private ObjectId id;
    private String userName;
    private String email;

    private String about;

    private String imageUrl;

    private String publicId;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private List<String> role;

}
