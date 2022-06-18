package chao.wonderland;

//import chao.wonderland.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application {
//	@Autowired
//	public UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

//			LocalDate startDate =  LocalDate.now();
//			var newDate = startDate.datesUntil( LocalDate.of(2022,07,01)).collect(Collectors.toList());
//			System.out.println("----date----" + newDate);

		}


}
