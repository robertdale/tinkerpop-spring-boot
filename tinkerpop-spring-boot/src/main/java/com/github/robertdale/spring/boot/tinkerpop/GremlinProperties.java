package com.github.robertdale.spring.boot.tinkerpop;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = GremlinProperties.PREFIX)
public class GremlinProperties {

	public static final String PREFIX = "gremlin";
}
