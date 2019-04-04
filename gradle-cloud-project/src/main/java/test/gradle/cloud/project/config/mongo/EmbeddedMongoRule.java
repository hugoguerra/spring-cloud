package test.gradle.cloud.project.config.mongo;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class EmbeddedMongoRule extends AbstractMongoConfiguration {

    private MongodProcess mongod;

    private MongoClient mongoClient;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    public EmbeddedMongoRule() {}

    public EmbeddedMongoRule(String host, int port, String database) {
        this.database = database;
        this.port = port;
        this.host = host;
    }

    @Override
    protected String getDatabaseName() {
        return null;
    }

    @Override
    public MongoClient mongoClient() {
        return start();
    }

    public MongoClient start(){
        try {
            if (mongod == null) {
                port = findFreePort();
                IRuntimeConfig config = new RuntimeConfigBuilder().defaults(Command.MongoD).build();
                MongodStarter runtime = MongodStarter.getInstance(config);

                IMongodConfig mongodConfig = new MongodConfigBuilder()
                        .version(Version.Main.V3_4)
                        .net(new Net(this.host, this.port, Network.localhostIsIPv6()))
                        .build();

                MongodExecutable mongodExecutable = runtime.prepare(mongodConfig);
                mongod = mongodExecutable.start();
            }

            this.mongoClient = new MongoClient(this.host, this.port);
            return this.mongoClient;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stopMongo() {
        if (mongod != null) {
            mongod.stop();
        }
    }

    public void after() {
        if (mongoClient != null) {
            mongoClient.getDatabase(this.database).drop();
        }
    }

    private static int findFreePort() {
        int p = -1;
        try (ServerSocket server = new ServerSocket(0)) {
            p = server.getLocalPort();
            server.close();
        } catch (IOException ex) {
        }

        return p;
    }
}
