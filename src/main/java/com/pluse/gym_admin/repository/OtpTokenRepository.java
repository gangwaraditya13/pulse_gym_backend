package com.pluse.gym_admin.repository;

import com.pluse.gym_admin.entity.OtpToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpTokenRepository extends MongoRepository<OtpToken, ObjectId> {

    Optional<OtpToken> findByUserName(String userName);
    void deleteByUserName(String userName);
}
