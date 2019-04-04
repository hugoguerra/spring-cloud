package test.gradle.cloud.project.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import test.gradle.cloud.project.entity.ClientEntity;

public interface ClientRepository extends MongoRepository<ClientEntity, ObjectId> {
}
