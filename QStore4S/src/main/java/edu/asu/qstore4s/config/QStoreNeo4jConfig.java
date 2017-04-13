package edu.asu.qstore4s.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "edu.asu.qstore4s.repository")
@EnableTransactionManagement
public class QStoreNeo4jConfig extends Neo4jConfiguration {

    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("edu.asu.qstore4s.domain");
    }

    @Bean(name = "neo4jOperations")
    public Neo4jOperations Neo4jOperations() throws Exception {
        return new Neo4jTemplate(getSession());
    }
}
