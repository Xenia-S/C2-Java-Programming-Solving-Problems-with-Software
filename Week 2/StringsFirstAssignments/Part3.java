import edu.duke.*;
import java.io.*;

public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
       if (stringb.indexOf(stringa) != -1) {
            if ((stringb.indexOf(stringa, stringb.indexOf(stringa) + stringa.length()) != -1)) {
                return true;
            }
        }
       return false;
    }
    
    public String lastPart(String stringa, String stringb) {
        if (stringb.indexOf(stringa) != -1) {
            return stringb.substring(stringb.indexOf(stringa)+stringa.length());
        }
        return stringb;
    }
    
    public void test() {
        String stringa = "a";
        String stringb = "kjargfjskskbajkb";
        System.out.println(stringa);
        System.out.println(stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        System.out.println(lastPart(stringa, stringb));
    
        stringa = "by";
        stringb = "A story by Abby Long";
        System.out.println(stringa);
        System.out.println(stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        System.out.println(lastPart(stringa, stringb));
    
        stringa = "an";
        stringb = "banana";
        System.out.println(stringa);
        System.out.println(stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        System.out.println(lastPart(stringa, stringb));
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println(stringa);
        System.out.println(stringb);
        System.out.println(twoOccurrences(stringa, stringb));
        System.out.println(lastPart(stringa, stringb));
    }
}
