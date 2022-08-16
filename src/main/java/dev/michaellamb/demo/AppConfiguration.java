package dev.michaellamb.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.oas.annotations.EnableOpenApi;

@Configuration
@EnableWebMvc
@ComponentScan("dev.michaellamb.demo")
@EnableOpenApi
@ConfigurationProperties(prefix="dev.michaellamb")
public class AppConfiguration {
}