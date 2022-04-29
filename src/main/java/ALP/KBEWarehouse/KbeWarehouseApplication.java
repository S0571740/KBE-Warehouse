package ALP.KBEWarehouse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		Gson gson=new Gson();
		List<Component> components = CSVParser.parse();
		if (!id.equals("")) {
			components = components.stream().filter(cmp -> cmp.getKomponententyp().equals(id)).toList();
		}
		String returnString=gson.toJson(components);
		return returnString;
	}

	@GetMapping("/components/{id}")
	public ResponseEntity<String> getComponentWithId(@PathVariable("id") int objectId)	{
		Gson gson=new Gson();
		Component component=null;
		List<Component> components = CSVParser.parse();
		try	{
			component = components.get(objectId);
		} catch(IndexOutOfBoundsException e)	{
			//TODO: status auf 404
			return new ResponseEntity<>(
					"component with specified id doesnt exist",
					HttpStatus.NOT_FOUND
			);
		}
		String returnString=gson.toJson(component);
		return new ResponseEntity<>(
				gson.toJson(component),
				HttpStatus.OK
		);
	}
}
