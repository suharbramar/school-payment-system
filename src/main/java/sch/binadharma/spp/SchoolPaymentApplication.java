package sch.binadharma.spp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
<<<<<<< HEAD:src/main/java/io/bramcode/movie/moviecategoryservices/MovieCategoryServicesApplication.java
@EnableEurekaClient
@EnableCaching
public class MovieCategoryServicesApplication {
=======
//@EnableEurekaClient
public class SchoolPaymentApplication {
>>>>>>> c977bf7f2ce2e55e0f355cc0c1034b36defbce8c:src/main/java/sch/binadharma/spp/SchoolPaymentApplication.java

	//singleton
	//rest template map to this one instance
	//bean as producer, anybody autowired this method then it will inject
	//the return type must be same with return type specifed in autowired
//	@Bean
//	@LoadBalanced
//	public RestTemplate getRestTemplate(){
//       return new RestTemplate();
//	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolPaymentApplication.class, args);
	}

}
