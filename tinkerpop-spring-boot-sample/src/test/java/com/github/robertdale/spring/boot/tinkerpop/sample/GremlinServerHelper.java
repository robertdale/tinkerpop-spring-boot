package com.github.robertdale.spring.boot.tinkerpop.sample;

import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.tinkerpop.gremlin.server.GremlinServer;
import org.apache.tinkerpop.gremlin.server.Settings;
import org.springframework.stereotype.Component;

/**
 * Creates a re-usable instance of a server. You may want to @Before and @After
 * to prepare and/or clean up state in your tests.
 * 
 * @author rdale
 *
 */
@Component
public class GremlinServerHelper {

    private GremlinServer server;

    @PostConstruct
    public void startServer() throws Exception {
        final InputStream stream = this.getClass().getResourceAsStream("/gremlin-server.yaml");
        this.server = new GremlinServer(Settings.read(stream));

        server.start().join();
    }

    @PreDestroy
    public void stopServer() throws Exception {
        server.stop().join();
    }
}
