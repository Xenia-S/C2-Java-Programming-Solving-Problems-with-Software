import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class ParsingExportData {
    
    public void tester() {
        /* FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        parser = fr.getCSVParser();        
        numberOfExporters(parser, "gold");
        parser = fr.getCSVParser();        
        numberOfExporters(parser, "diamonds");
        
        parser = fr.getCSVParser();
        bigExporters (parser, "$999,999,999,999"); */
        
        // решение Graded Quiz:
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // listExportersTwoProducts(parser, "cotton", "flowers");
        // numberOfExporters(parser, "cocoa");
        // bigExporters (parser, "$999,999,999,999");
        
        
        
    }

    public void countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
           String currCountry = record.get("Country");
           if (currCountry.equals(country)) {     // не содержит, а точно совпадает
               System.out.println(record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        
        }
       
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");        
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count += 1;
            }
        }
        System.out.println("Total number of countries exporting " + exportItem + ": " + count);
    }
    
    public void bigExporters (CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String currAmount = record.get("Value (dollars)");
            if (currAmount.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
}
