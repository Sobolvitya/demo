package com.demo.Mongo;


import org.springframework.data.mongodb.repository.MongoRepository;


public interface TaskDescriptionRepository extends MongoRepository<TaskDescription, String> {
}
