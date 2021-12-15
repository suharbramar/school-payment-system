package io.bramcode.movie.moviecategoryservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MovieCategoryServicesApplication {

	//singleton
	//rest template map to this one instance
	//bean as producer, anybody autowired this method then it will inject
	//the return type must be same with return type specifed in autowired
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
       return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCategoryServicesApplication.class, args);
	}

}
