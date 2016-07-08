package com.github.robertdale.spring.boot.tinkerpop.sample;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphService {

	@Autowired
	private Graph graph;

	@Autowired
	private GraphTraversalSource g;

	public Graph graph() {
		return graph;
	}

	public GraphTraversalSource g() {
		return g;
	}

}
