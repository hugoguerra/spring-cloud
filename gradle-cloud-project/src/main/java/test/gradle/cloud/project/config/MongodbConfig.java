package test.gradle.cloud.project.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
@RefreshScope
public class MongodbConfig extends AbstractMongoConfiguration {

    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private String port;

    @Value("${mongodb.database}")
    private String database;

    /**
     * The maximum idle time of a pooled connection. A zero value indicates no limit to the idle time.
     * A pooled connection that has exceeded its idle time will be closed and replaced when necessary by a new connection.
     */
    @Value("${mongodb.max.connection.idle.time:60000}")
    private Integer maxConnectionIdleTime;

    /**
     * The connection timeout in milliseconds.
     * It is used solely when establishing a new connection
     *
     * Mongo driver default is 10,000. Retain the same value but allow for overrding if required.
     */
    @Value("${mongodb.max.connection.timeout:10000}")
    private Integer maxConnectionTimeout;

    /**
     * The minimum number of connections per host for this MongoClient instance.
     *
     *  Mongo driver default is 0. Overriding this to 100.
     */
    @Value("${mongodb.min.connections.per.host:50}")
    private Integer minConnectionsPerHost;

    /**
     * The maximum number of connections allowed per host for this MongoClient instance.
     * Those connections will be kept in a pool when idle.
     * Once the pool is exhausted, any operation requiring a connection will block waiting for an available connection.
     *
     * Mongo driver default is 100. Keep the same value. Placeholder here in case we want to override.
     */
    @Value("${mongodb.max.connections.per.host:100}")
    private Integer maxConnectionsPerHost;

    /**
     * This flag controls the socket keep alive feature that keeps a connection alive through firewalls
     *
     * Mongo driver default is false. Overriding to true.
     */
    @Value("${mongodb.socket.keep.alive:true}")
    private Boolean socketKeepAlive;

    @Override
    public MongoClient mongoClient() {

        MongoClientOptions.Builder builder = MongoClientOptions.builder().maxConnectionIdleTime(maxConnectionIdleTime)
                .connectTimeout(maxConnectionTimeout)
                .minConnectionsPerHost(minConnectionsPerHost).connectionsPerHost(maxConnectionsPerHost)
                .socketKeepAlive(socketKeepAlive);

        String uri = "mongodb://".concat(this.host).concat(":").concat(this.port);
        MongoClientURI mongoUri = new MongoClientURI(uri, builder);
        return new MongoClient(mongoUri);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoMappingContext mongoMappingContext() throws ClassNotFoundException {
        return super.mongoMappingContext();
    }
}
