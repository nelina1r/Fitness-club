package ru.t1.dedov.model.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document(collection = "userActions")
public class UserAction {

    @Id
    private ObjectId id;

    private String username;

    private String methodName;

    private LocalDateTime actionDateTime;

    private Object[] methodArgs;
}
