package com.github.robertdale.spring.boot.tinkerpop.sample;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TinkerpopApplication.class)
@TestPropertySource(locations = "file:config/application-tinker.properties")
public class TinkerDefaultGraphTest {

	@Autowired
	private GraphService service;

	@Test
	public void shouldFindGraph() {
		List<Vertex> vertices = service.g().V().toList();
		assertThat(vertices, empty());

		Vertex v = service.g().addV("person").property("name", "fred").next();
		vertices = service.g().V().toList();
		assertThat(vertices, not(empty()));
		assertThat((String) vertices.get(0).property("name").value(), is("fred"));
	}
}
