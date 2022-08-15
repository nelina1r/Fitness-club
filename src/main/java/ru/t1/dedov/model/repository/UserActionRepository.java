package ru.t1.dedov.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.t1.dedov.model.entity.UserAction;

@Repository
public interface UserActionRepository extends MongoRepository<UserAction, String> {
}
