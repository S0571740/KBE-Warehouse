package ALP.KBEWarehouse;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVParser {
    public static List<Component> parse() {
        List<Component> components = new LinkedList<>();
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/components.csv")).withSkipLines(1).build();
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
        return components;
    }
}