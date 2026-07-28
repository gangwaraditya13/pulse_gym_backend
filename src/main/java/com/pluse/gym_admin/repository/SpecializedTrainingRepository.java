package com.pluse.gym_admin.repository;

import com.pluse.gym_admin.entity.SpecializedTraining;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializedTrainingRepository extends MongoRepository<SpecializedTraining, ObjectId> {
}
