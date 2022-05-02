package ALP.KBEWarehouse;

import java.io.IOException;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@RestController
@EnableSwagger2
@Configuration
public class KbeWarehouseApplication {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(KbeWarehouseApplication.class, args);
	}

	@GetMapping("/")
	public String helloWorld(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/components")
	public String getComponents(@RequestParam(value = "id", defaultValue = "") String id) {
		List<Component> components = CSVParser.parse();
		String returnString = "";
		if (!id.equals("")) {
			components = components.stream().filter(cmp -> cmp.getKomponententyp().equals(id)).toList();
		}
		for(Component cmp : components) {
			returnString += cmp.toString();
		}
		return returnString;
	}
}
