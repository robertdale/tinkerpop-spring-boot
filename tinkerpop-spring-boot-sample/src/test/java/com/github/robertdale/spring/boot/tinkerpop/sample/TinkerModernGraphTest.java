package com.github.robertdale.spring.boot.tinkerpop.sample;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.util.function.Lambda;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TinkerModernGraphTest {

    private final Logger log = LoggerFactory.getLogger(TinkerModernGraphTest.class);

    @Autowired
    private GraphTraversalSource g;

    @Autowired
    private Client client;

    @Autowired
    private Cluster cluster;

    @Test
    public void shouldFindGraph() {
        List<Map<String, Object>> vertices = g.V().valueMap(true).toList();
        assertThat(vertices, not(empty()));
        vertices.stream().forEach(System.out::println);
    }

    @Test
    public void shouldAdd() {
        Vertex v = g.addV("person").property("name", "fred").next();
        assertThat(v, notNullValue());
        Map<String, Object> result = g.V().has("name", "fred").valueMap().next();
        assertThat(result, notNullValue());
        // valueMap values are always lists of values
        List values = (List) result.get("name");
        assertThat((String) values.get(0), is("fred"));
    }

    @Test
    public void shouldSubmitScript() throws InterruptedException, ExecutionException {
        List<Result> results = client.submit("g.V().valueMap()").all().get();
        assertThat(results, notNullValue());
        assertThat(results, not(empty()));
        results.stream().forEach(System.out::println);
    }

    @Test
    public void connectionTests() {

        List<Thread> threads = new LinkedList<>();

        for (int i = 0; i < 20; i++) {
            final int threadnum = i;
            Runnable runnable = () -> {
                log.info("[{}] Starting sleepy call ", threadnum);
                g.V().map(Lambda.function("Thread.sleep(1000); return it;")).toList();
                log.info("[{}] Done", threadnum);
            };
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

}
