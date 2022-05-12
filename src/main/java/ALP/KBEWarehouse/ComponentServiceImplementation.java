package ALP.KBEWarehouse;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ComponentServiceImplementation implements ComponentService {

    @Autowired
    ComponentRepository componentRepository;

    @Override
    public void readCSV(MultipartFile file) {

        List<Component> components = new LinkedList<>();
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(file.getOriginalFilename())).withSkipLines(1).build();
            components = reader.readAll().stream().map(data -> {
                Component component = new Component();
                component.setKomponententyp(data[0]);
                component.setName(data[1]);
                component.setMaterial(data[2]);
                component.setHerkunft(data[3]);
                component.setFarbe(data[4]);
                component.setHersteller(data[5]);
                component.setForm(data[6]);
                component.setGebrauchszustand(data[7]);
                component.setPreis(data[8]);
                component.setVerfuegbarkeit(data[9]);
                component.setKlangauswirkung(data[10]);
                return component;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        componentRepository.saveAll(components);
    }
    
    @Override
    public Component getComponentByName(String name) {
        List<Component> toReturn = List.copyOf(parseIterableToList(componentRepository.findAll()));
        return toReturn.stream()
                .filter(comp -> comp.getName().equals(name))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public List<Component> getComponents() {
        return parseIterableToList(componentRepository.findAll());
    }

    @Override
    public List<Component> getComponentsOfType(String type) {
        List<Component> toReturn = List.copyOf(parseIterableToList(componentRepository.findAll()));
        return toReturn.stream()
                .filter(comp -> comp.getKomponententyp().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public List<Component> getComponentOfTypeAndName(String typeKey, String typeValue) {
        List<Component> toReturn = List.copyOf(parseIterableToList(componentRepository.findAll()));
        switch (typeKey) {
            case "komponententyp":
                return toReturn.stream()
                        .filter(comp -> comp.getKomponententyp().equals(typeValue))
                        .collect(Collectors.toList());
            case "name":
                return toReturn.stream()
                        .filter(comp -> comp.getName().equals(typeValue))
                        .collect(Collectors.toList());
            case "material":
                return toReturn.stream()
                        .filter(comp -> comp.getMaterial().equals(typeValue))
                        .collect(Collectors.toList());
            case "herkunft":
                return toReturn.stream()
                        .filter(comp -> comp.getHerkunft().equals(typeValue))
                        .collect(Collectors.toList());
            case "farbe":
                return toReturn.stream()
                        .filter(comp -> comp.getFarbe().equals(typeValue))
                        .collect(Collectors.toList());
            case "hersteller":
                return toReturn.stream()
                        .filter(comp -> comp.getHersteller().equals(typeValue))
                        .collect(Collectors.toList());
            case "form":
                return toReturn.stream()
                        .filter(comp -> comp.getForm().equals(typeValue))
                        .collect(Collectors.toList());
            case "gebrauchszustand":
                return toReturn.stream()
                        .filter(comp -> comp.getGebrauchszustand().equals(typeValue))
                        .collect(Collectors.toList());
            case "preis":
                return toReturn.stream()
                        .filter(comp -> comp.getPreis().equals(typeValue))
                        .collect(Collectors.toList());
            case "verfuegbarkeit":
                return toReturn.stream()
                        .filter(comp -> comp.getVerfuegbarkeit().equals(typeValue))
                        .collect(Collectors.toList());
            case "klangauswirkung":
                return toReturn.stream()
                        .filter(comp -> comp.getKlangauswirkung().equals(typeValue))
                        .collect(Collectors.toList());
        }
        return new LinkedList<>();
    }

    private List<Component> parseIterableToList(Iterable<Component> toParse){
        List<Component> components = new LinkedList<>();
        toParse.forEach(component -> components.add(component));
        return components;
    }
}
