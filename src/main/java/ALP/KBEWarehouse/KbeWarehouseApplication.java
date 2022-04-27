package ALP.KBEWarehouse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

@SpringBootApplication
@RestController
public class KbeWarehouseApplication {

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
		if (id.equals("")) {
			return components.toString();
		} else {
			return components.stream().filter(cmp -> cmp.getKomponententyp().equals(id)).toList().toString();
		}
	}
}
