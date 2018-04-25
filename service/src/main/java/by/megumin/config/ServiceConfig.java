package by.megumin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ComponentScan(basePackages = {"by.megumin"})
@ContextConfiguration(classes = DatabaseConfig.class)
@Import(value = {DatabaseConfig.class, AspectConfig.class})
public class ServiceConfig {
}
