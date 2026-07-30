package com.pluse.gym_admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Data
@Document(collection = "gym_info")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class GymInfo {
    @Id
    private ObjectId id;
    private String gymName;
    private String gymLocation;
    private String gymEmail;
    private String gymPhone;
    private HashMap<String, String> socialMediaIdUrl = new HashMap<>();
}