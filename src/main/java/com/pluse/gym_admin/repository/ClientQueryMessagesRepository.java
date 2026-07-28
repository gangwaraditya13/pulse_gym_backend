package com.pluse.gym_admin.repository;

import com.pluse.gym_admin.entity.ClientQueryMessages;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientQueryMessagesRepository extends MongoRepository<ClientQueryMessages, ObjectId> {
}
