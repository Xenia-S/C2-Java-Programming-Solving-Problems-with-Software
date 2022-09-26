import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp && currentTemp > -9999) {
                    coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestTemp = coldestHourInFile(parser);
        System.out.println("Coldest temperature " + coldestTemp.get("TemperatureF") + " at " + coldestTemp.get("DateUTC"));    
    }
    
    public String printAllTemperatures(File f) {
        String answer = "";
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord row : parser) {
            answer = answer + "\n" + row.get("DateUTC") + " " + row.get("TemperatureF");
        }
        return answer;
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord coldestFromSelectedFiles = null;
        File coldestDay = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldestFromFile = coldestHourInFile(parser);
            if (coldestFromSelectedFiles == null) {
                coldestFromSelectedFiles = coldestFromFile;
                coldestDay = f;
            }
            else {
                double currentTemp = Double.parseDouble(coldestFromFile.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestFromSelectedFiles.get("TemperatureF"));
                if (currentTemp < coldestTemp) {
                    coldestFromSelectedFiles = coldestFromFile;
                    coldestDay = f;
                }
            }
                       
        }
        
        String answer = "Coldest day was in file " + coldestDay.getName() + "\nColdest temperature on that day was " + coldestFromSelectedFiles.get("TemperatureF") + "\nAll the Temperatures on the coldest day were:" + printAllTemperatures(coldestDay);
        return answer;
    }
    
    public void testFileWithColdestTemperature() {
        System.out.println(fileWithColdestTemperature());
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRow = null;
        for (CSVRecord row : parser) {
            lowestHumidityRow = findLowestHumidityRow(lowestHumidityRow, row);            
        }
        return lowestHumidityRow;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord LowestHumidity = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + LowestHumidity.get("Humidity") + " at " + LowestHumidity.get("DateUTC"));
    }
    
    public CSVRecord findLowestHumidityRow(CSVRecord lowestHumidityRow, CSVRecord row) {
        if (lowestHumidityRow == null) {
                lowestHumidityRow = row;
            }
            // исключить перевод в int ячеек со значением N/A
            if (!row.get("Humidity").contains("N/A")) {
                int currHumidity = Integer.parseInt(row.get("Humidity"));
                int minHumidity = Integer.parseInt(lowestHumidityRow.get("Humidity"));
                if (currHumidity < minHumidity) {
                    lowestHumidityRow = row;
                }
            }
        return lowestHumidityRow;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRow = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowestHumidityInFileRow = lowestHumidityInFile(parser);
            lowestHumidityRow = findLowestHumidityRow(lowestHumidityRow, lowestHumidityInFileRow);
        }        
        return lowestHumidityRow;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityInSelectedFiles = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidityInSelectedFiles.get("Humidity") + " at " + lowestHumidityInSelectedFiles.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sumTemperature = 0.0;
        int sumRecords = 0;
        for (CSVRecord row : parser) {
            double currTemperature = Double.parseDouble(row.get("TemperatureF"));
            sumTemperature = sumTemperature + currTemperature;
            sumRecords = sumRecords + 1;
        }
        double averageTemperature = sumTemperature / sumRecords;
        return averageTemperature;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperature = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + averageTemperature);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sumTemperature = 0.0;
        int sumRecords = 0;
        for (CSVRecord row : parser) {
            if (!row.get("Humidity").contains("N/A") && Integer.parseInt(row.get("Humidity")) >= value) {
                double currTemperature = Double.parseDouble(row.get("TemperatureF"));
                sumTemperature = sumTemperature + currTemperature;
                sumRecords = sumRecords + 1;
            }
        }
        double averageTemperature = sumTemperature / sumRecords;
        return averageTemperature;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemperatureWithHighHumidity = averageTemperatureWithHighHumidityInFile(parser, 80);
        // we can use the expression “x != x”. This expression returns true only for NAN. https://www.baeldung.com/java-not-a-number
        if (averageTemperatureWithHighHumidity != averageTemperatureWithHighHumidity) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average temperature in file is " + averageTemperatureWithHighHumidity);
        }
    }
}
