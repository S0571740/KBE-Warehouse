package ALP.KBEWarehouse;

import java.util.List;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ComponentController {

    ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    /**
     * Get mapping that returns all components of given type. If no type was specified, all components are returned.
     * @param id the type of the component
     * @return all components (of given type)
     */

    @GetMapping("/components")
    public String getComponents(@RequestParam(value = "id", defaultValue = "") String id) {
        Gson gson=new Gson();
        List<Component> components = null;
        if (!id.equals("")) {
            components = componentService.getComponentsOfType(id);
        } else components=componentService.getComponents();
        String returnString=gson.toJson(components);
        return returnString;
    }

    @GetMapping("/components/{id}")
    public ResponseEntity<String> getComponentWithId(@PathVariable("id") int objectId)	{
        Gson gson=new Gson();
        Component component=null;
        try	{
            component=componentService.getComponentById(objectId);
        } catch(IndexOutOfBoundsException e)	{
            return new ResponseEntity<>(
                    "component with specified id doesnt exist",
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                gson.toJson(component),
                HttpStatus.OK
        );
    }

    /**
     * Post mapping for uploading and saving CSV file into the DB
     * @param file the csv file to be uploaded
     * @return a success message
     */
    @PostMapping("/uploadComponents")
    public String uploadComponents(@RequestParam("file") MultipartFile file) {
        componentService.readCSV(file);
        return "Success: Components were updated";
    }
}