package by.megumin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "by.megumin.controller")
@EnableWebMvc
@Import(value = {InternationalizationConfig.class, ThymeleafConfig.class})
public class WebConfig {
}
