package dev.michaellamb.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableOpenApi
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

/**
 * The hello function returns a greeting to the user.
 * 
 *
 * @param = Set the default value of a parameter
 * @param defaultValue Set the default value of a parameter
 *
 * @return "Hello " concatenated with the name parameter or a default value 
 *
 * @docauthor Trelent, michaellambgelo
 */
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

/**
 * The commandLineRunner function is a Spring Boot function that is called when the application starts.
 * It is used to print out some useful information about what beans are available in our application context.
 
 *
 * @param ctx Get access to the beans provided by Spring Boot
 *
 * @return CommandLineRunner
 *
 * @docauthor Trelent, michaellambgelo
 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

/**
 * The restTemplate function is a factory function that creates an instance of the RestTemplate class.
 * The restTemplate function is provided by Spring Framework and it takes in a RestTemplateBuilder object as its parameter.
 * The RestTemplateBuilder class allows you to configure the rest template with specific configurations such as message converters, error handlers, etc. 
 
 *
 * @param builder Configure the rest template
 *
 * @return A new instance of the RestTemplate class
 *
 * @docauthor Trelent, michaellambgelo
 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
