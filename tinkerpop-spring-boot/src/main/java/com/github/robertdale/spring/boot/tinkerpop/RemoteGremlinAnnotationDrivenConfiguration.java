package com.github.robertdale.spring.boot.tinkerpop;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.remote.RemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemoteGremlinAnnotationDrivenConfiguration {

    @Value("${gremlin.config}")
    private String config;

    @Value("${gremlin.graph}")
    private String remoteTraversalSourceName;

    @ConditionalOnMissingBean
    @Bean
    public Cluster cluster() throws Exception {
        return Cluster.open(config);
    }

    @ConditionalOnMissingBean
    @Bean
    public Client client() throws Exception {
        return cluster().connect();
    }

    @ConditionalOnMissingBean
    @Bean
    public RemoteConnection driverRemoteConnection(final Cluster cluster) {
        return DriverRemoteConnection.using(cluster, remoteTraversalSourceName);
    }

    @ConditionalOnMissingBean
    @Bean
    public Graph emptyGraph() {
        return EmptyGraph.instance();
    }

    @ConditionalOnMissingBean
    @Bean
    public GraphTraversalSource graphTraversalSource(final RemoteConnection remoteConnection) {
        return emptyGraph().traversal().withRemote(remoteConnection);
    }

}
