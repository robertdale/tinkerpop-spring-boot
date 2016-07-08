package com.github.robertdale.spring.boot.tinkerpop;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.remote.RemoteGraph;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "gremlin.embedded", name = "enabled", havingValue = "false", matchIfMissing = true)
// @EnableConfigurationProperties(GremlinProperties.class)
public class RemoteGremlinAnnotationDrivenConfiguration {

	@Value("${gremlin.cluster}")
	private String cluster;

	@Value("${gremlin.graph}")
	private String graph;

	@ConditionalOnMissingBean
	@Bean
	public Cluster cluster() throws Exception {
		return Cluster.open(cluster);
	}

	@ConditionalOnMissingBean
	@Bean
	public Client client() throws Exception {
		return cluster().connect();
	}

	@ConditionalOnMissingBean
	@Bean
	public Graph remoteGraph(Cluster cluster) {
		return RemoteGraph.open(DriverRemoteConnection.using(cluster, graph));
	}

}
