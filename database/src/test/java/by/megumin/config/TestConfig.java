package by.megumin.config;

import by.megumin.config.DatabaseConfig;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ContextConfiguration(classes = DatabaseConfig.class)
@ComponentScan(basePackages = {"by.megumin.dao", "by.megumin.util"})
@Import(DatabaseConfig.class)
public class TestConfig {

    @Value("${hibernate.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.url}")
    private String url;

    @Value("${hibernate.creation_policy}")

    @Bean
    public JdbcDataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}