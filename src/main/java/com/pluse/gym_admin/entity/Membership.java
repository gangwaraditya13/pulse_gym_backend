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
@Document(collection = "membership")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Membership {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("_id")
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String planName;
    @NonNull
    private String planDuration;
    @NonNull
    private int planPrice = 0;

    private int discount = 0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
