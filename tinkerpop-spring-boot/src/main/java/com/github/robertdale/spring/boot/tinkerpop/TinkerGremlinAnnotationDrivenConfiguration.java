package com.github.robertdale.spring.boot.tinkerpop;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerFactory;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "gremlin.embedded", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TinkerGremlinAnnotationDrivenConfiguration {

	@ConditionalOnProperty(prefix = "gremlin.embedded", name = "factory", havingValue = "default", matchIfMissing = true)
	@Bean
	public Graph graphDefault() {
		return TinkerGraph.open();
	}

	@ConditionalOnProperty(prefix = "gremlin.embedded", name = "factory", havingValue = "classic", matchIfMissing = false)
	@Bean
	public Graph graphClassic() {
		return TinkerFactory.createClassic();
	}

	@ConditionalOnProperty(prefix = "gremlin.embedded", name = "factory", havingValue = "modern", matchIfMissing = false)
	@Bean
	public Graph graphModern() {
		return TinkerFactory.createModern();
	}

	@ConditionalOnProperty(prefix = "gremlin.embedded", name = "factory", havingValue = "crew", matchIfMissing = false)
	@Bean
	public Graph graphCrew() {
		return TinkerFactory.createTheCrew();
	}

}
