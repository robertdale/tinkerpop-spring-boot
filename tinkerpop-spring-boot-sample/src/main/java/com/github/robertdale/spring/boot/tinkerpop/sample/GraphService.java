package com.github.robertdale.spring.boot.tinkerpop.sample;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphService {

    @Autowired
    private GraphTraversalSource g;

    public GraphTraversalSource g() {
        return g;
    }

}
