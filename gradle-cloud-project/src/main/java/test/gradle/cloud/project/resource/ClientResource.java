package test.gradle.cloud.project.resource;

import com.mongodb.MongoClientOptions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.gradle.cloud.project.entity.ClientEntity;
import test.gradle.cloud.project.repository.ClientRepository;

import java.util.List;

@RefreshScope
@RequestMapping("gradle/client")
@RestController
@Api(description = "This the Client Resource Documentation")
public class ClientResource {

    public ClientResource() {}

    @Value("${message:cloud config - message test}")
    private String message;

    private ClientRepository repository;
    private MongoOperations mongoOperations;

    @Autowired
    public ClientResource(ClientRepository repository, MongoOperations mongoOperations) {
        this.repository = repository;
        this.mongoOperations = mongoOperations;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "search all Clients")
    public ResponseEntity<List<ClientEntity>> search() {
        try {
            return ResponseEntity.ok(ClientEntity.findAll(repository));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "save client in the db")
    public ResponseEntity<Boolean> save(@RequestBody ClientEntity clientEntity) {
        try {
            ClientEntity entity = clientEntity.save(repository);
            return ResponseEntity.ok(entity.getId() != null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "search client by id")
    public ResponseEntity<ClientEntity> search(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(ClientEntity.findById(repository, new ObjectId(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "update client in the db")
    public ResponseEntity<Boolean> update(@RequestBody ClientEntity clientEntity) {
        try {
            ClientEntity entity = clientEntity.save(repository);
            return ResponseEntity.ok(entity.getId() != null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/cloud", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "return the variable message config in the cloud server")
    public ResponseEntity<String> message() {
        try {
            System.out.println(this.message);
            return ResponseEntity.ok(this.message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
