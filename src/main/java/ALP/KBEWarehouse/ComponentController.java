package ALP.KBEWarehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ComponentController {

    ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping("/components")
    public String getComponents(@RequestParam(value = "id", defaultValue = "") String id) {
        
        List<Component> components = CSVParser.parse();
        String returnString = "";
        if (!id.equals("")) {
            components = componentService.getComponentsOfType(id);
        }
        for (Component cmp : components) {
            returnString += cmp.toString();
        }
        return returnString;
    }

    @PostMapping("/uploadComponents")
    public String uploadComponents(@RequestParam("file") MultipartFile file) {
        componentService.readCSV(file);
        return "Success: Components were updated";
    }
}
