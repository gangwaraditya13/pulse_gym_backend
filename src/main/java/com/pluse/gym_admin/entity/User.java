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
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonProperty("_id")
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    @NonNull
    private String Password;
    @NonNull
    @Indexed(unique = true)
    private String email;
    @NonNull
    private String about;

    private String imageUrl;

    private String publicId;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private List<String> role = new ArrayList<>();
}
