import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {
    public void totalBirths() {
        FileResource fr = new FileResource();
        int totalBirth = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int boysNames = 0;
        int girsNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            totalBirth += Integer.parseInt(rec.get(2));
            //System.out.println(rec.get(1) + " " + Integer.parseInt(rec.get(2)));  // проверка
            if (rec.get(1).equals("M")) {
                totalBoys += Integer.parseInt(rec.get(2));
                boysNames += 1;
            }
            else {
                totalGirls += Integer.parseInt(rec.get(2));
                girsNames +=1;
            }
        }
        System.out.println("Total births " + totalBirth);
        System.out.println("Total boys " + totalBoys);
        System.out.println("Total girls " + totalGirls);
        System.out.println("Total boys names " + boysNames);
        System.out.println("Total girs names " + girsNames);
    }

    public int getRank(int year, String name, String gender) {
        if (Integer.toString(year).length() < 4 || Integer.toString(year).length() > 4) {
            return -1; // если год вписан в неправильном формате, не будет ошибки
        }
        // FileResource fr = new FileResource("us_babynames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank() {
        
        /*
        System.out.println(getRank(2012, "Emma", "F"));       // 2
        System.out.println(getRank(2012, "Emma", "M"));       // -1
        System.out.println(getRank(2013, "Noah", "M"));       // 1
        System.out.println(getRank(2013, "Noah", "F"));       // -1
        System.out.println(getRank(2012, "Noah", "M"));       // 4
        System.out.println(getRank(2014, "William", "M"));    // 5
        System.out.println(getRank(2014, "William", "F"));    // -1
        System.out.println(getRank(14, "William", "M"));      // -1
        System.out.println(getRank(141414, "William", "M"));  // -1
        System.out.println(getRank(2012, "Ema", "F"));        // -1
        */
        
        System.out.println(getRank(1960, "Emily", "F"));
        System.out.println(getRank(1971, "Frank", "M"));
        
    }
    
    public String getName(int year, int rank, String gender) {
        String name = "NO NAME";
        int count = 0;
        // FileResource fr = new FileResource("us_babynames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                count += 1;
                if (count == rank) {
                    name = rec.get(0);
                }
            }
        }
        return name;
    }
    
    public void testGetName() {
        /*
        System.out.println(getName(2012, 1, "M")); // Jacob
        System.out.println(getName(2012, 2, "F")); // Emma
        System.out.println(getName(2012, 3, "M")); // Ethan
        System.out.println(getName(2012, 10, "F")); // NO NAME
        System.out.println(getName(2013, 4, "M")); // Mason
        System.out.println(getName(2013, 5, "F")); // Ava
        System.out.println(getName(2014, 4, "M")); // Jacob
        System.out.println(getName(2014, 6, "F")); // NO NAME
        */
       
       System.out.println(getName(1980, 350, "F"));
       System.out.println(getName(1982, 450, "M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        if (rank == -1) {
            System.out.println("There is no " + name + " " + gender + " gender in " + year);
        }
        else {
            String newName = getName(newYear, rank, gender);
            System.out.println(name + " born in " + year + " would be " + newName + " in " + newYear);
        }
    }
    
    public void testWhatIsNameInYear() {
        /*
        whatIsNameInYear("Sophia", 2012, 2013, "F"); // Sophia
        whatIsNameInYear("Sophia", 2012, 2013, "M"); // no
        whatIsNameInYear("Sophia", 2012, 2014, "F"); // Emma
        whatIsNameInYear("Jacob", 2012, 2013, "F"); // no
        whatIsNameInYear("Jacob", 2012, 2013, "M"); // Noah
        whatIsNameInYear("Mason", 2014, 2012, "M"); // Ethan
        */
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int rank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String currYearString = f.getName().substring(3,7);
            int currYearInt = Integer.parseInt(currYearString);
            int currRank = getRank(currYearInt, name, gender);
            if (rank == 0 && currRank > -1) {
                rank = currRank;
                year = currYearInt;
            }
            if (currRank < rank && currRank > -1) {
                rank = currRank;
                year = currYearInt;
            }
        }
        return year;
    }
    
    public void testYearOfHighestRank() {
        /*
        System.out.println(yearOfHighestRank("Mason", "M")); // 2012
        System.out.println(yearOfHighestRank("Olivia", "M")); // -1
        System.out.println(yearOfHighestRank("Olivia", "F")); // 2014
        System.out.println(yearOfHighestRank("Noah", "M")); // 2013
         */
        
        System.out.println(yearOfHighestRank("Genevieve", "F"));
        System.out.println(yearOfHighestRank("Mich", "M"));
        
    }
    
    public double getAverageRank(String name, String gender) {
        int count = 0;
        double sumRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String yearString = f.getName().substring(3,7);
            int year = Integer.parseInt(yearString);
            sumRank += getRank(year, name, gender);
            if (getRank(year, name, gender) == -1) {
                return -1.0;
            }
            count += 1;
        }
        return sumRank / count;
    }
    
    public void testGetAverageRank() {
        /*
        System.out.println(getAverageRank("Mason" , "M"));  // 3.0
        System.out.println(getAverageRank("Jacob" , "M"));  // 2.66
        System.out.println(getAverageRank("Jb" , "M"));  // -1.0
        System.out.println(getAverageRank("Mason" , "F"));  // -1.0
        */
       
        System.out.println(getAverageRank("Susan" , "F"));
        System.out.println(getAverageRank("Robert" , "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int total = 0;
        // FileResource fr = new FileResource("us_babynames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                break;
            }
            if (rec.get(1).equals(gender)) {
                total += Integer.parseInt(rec.get(2));
            }
        }
        return total;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        /*
        System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M")); // 15
        System.out.println(getTotalBirthsRankedHigher(2013, "Liam", "M")); // 12
        System.out.println(getTotalBirthsRankedHigher(2013, "Liam", "F")); // 39 - выдаст всех девочек
        */
       
       System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F"));
       System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
