package com.github.robertdale.spring.boot.tinkerpop.sample;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TinkerpopApplication.class)
@TestPropertySource(locations = "file:config/application-tinker-modern.properties")
public class TinkerModernGraphTest {

	@Autowired
	private GraphService service;

	@Test
	public void shouldFindGraph() {
		List<Map<String, Object>> vertices = service.g().V().valueMap(true).toList();
		assertThat(vertices, not(empty()));
		vertices.stream().forEach(System.out::println);
	}
}
