package com.pluse.gym_admin.repository;

import com.pluse.gym_admin.entity.GymInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GymInfoRepository extends MongoRepository<GymInfo, ObjectId> {
}
