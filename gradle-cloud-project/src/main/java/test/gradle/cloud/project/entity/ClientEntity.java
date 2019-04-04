package test.gradle.cloud.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import test.gradle.cloud.project.repository.ClientRepository;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "cli")
/**
 * @author Hugo Guerra
 */
public class ClientEntity {

    @Id
    private ObjectId id;

    @Field("n")
    private String name;

    @Field("l")
    private String lastName;

    @Field("a")
    private Integer age;

    /**
     * Save or update the data client
     * @param repository
     * @return
     */
    public ClientEntity save(ClientRepository repository) {
        ClientEntity save = repository.save(this);
        return save;
    }

    /**
     * Delete the client
     * @param repository
     */
    public void delete(ClientRepository repository) {
        repository.delete(this);
    }

    /**
     * Find all the clients
     * @param repository
     * @return
     */
    public static List<ClientEntity> findAll(ClientRepository repository) {
        return repository.findAll();
    }

    /**
     * Find an specific client by ID
     * @param repository
     * @param id
     * @return
     */
    public static ClientEntity findById(ClientRepository repository, ObjectId id) {
        return repository.findById(id).get();
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
