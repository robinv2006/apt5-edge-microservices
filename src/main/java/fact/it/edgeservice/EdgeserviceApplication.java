package fact.it.edgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EdgeserviceApplication {
    @Bean
    public RestTemplate getRestTemplate(){return new RestTemplate();}

    public static void main(String[] args) {
        SpringApplication.run(EdgeserviceApplication.class, args);
    }

}
