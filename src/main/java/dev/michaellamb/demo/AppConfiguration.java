package dev.michaellamb.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("dev.michaellamb.demo")
@ConfigurationProperties(prefix="dev.michaellamb")
public class AppConfiguration {
}