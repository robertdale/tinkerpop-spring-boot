package com.github.robertdale.spring.boot.tinkerpop;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GremlinAnnotationDrivenConfiguration {

	@ConditionalOnMissingBean
	@Bean
	public GraphTraversalSource graphTraversalSource(Graph graph) {
		return graph.traversal();
	}
}
