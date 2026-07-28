package com.pluse.gym_admin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDateTime;

@Document(collection = "client_query_messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ClientQueryMessages {

    @Id
    @JsonProperty("_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @NonNull
    private String fullName;
    @NonNull
    private String email;
    @NonNull
    private String message;

    private LocalDateTime createdAt;

}
