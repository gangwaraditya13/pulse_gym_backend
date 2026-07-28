package com.pluse.gym_admin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;
@Data
@Document(collection = "program")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class SpecializedTraining {
    @Id
    @JsonProperty("_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String programName;
    @NonNull
    private String programDescription;

    private LocalDateTime createAt;

    private LocalDateTime UpdateAt;

}
