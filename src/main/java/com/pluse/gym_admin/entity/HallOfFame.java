package com.pluse.gym_admin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;
@Data
@Document(collection = "achievement")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class HallOfFame {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("_id")
    private ObjectId id;
    @NonNull
    private String championName;
    @NonNull
    private String championship;

    private String ImageUrl;

    private String publicId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
