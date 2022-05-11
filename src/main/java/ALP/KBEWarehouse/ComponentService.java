package ALP.KBEWarehouse;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ComponentService {
    public void readCSV(MultipartFile multipartFile);
    public Component getComponentByName(String name);
    public List<Component> getComponents();
    public List<Component> getComponentsOfType(String type);
    public List<Component> getComponentOfTypeAndName(String typeKey, String typeValue);
}
