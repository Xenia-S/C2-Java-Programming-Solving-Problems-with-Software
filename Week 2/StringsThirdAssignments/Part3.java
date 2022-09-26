
import edu.duke.*;
import java.io.*;

public class Part3 {
    public void processGenes(StorageResource sr) {
        int count9 = 0;
        int countCG = 0;
        String longestGene = "";
        for (String s : sr.data()) {
            if (s.length() > 9) {
                System.out.println(s + " > 9");
                count9 ++;
            }
            if (cgRatio(s) > 0.35) {
                System.out.println(s + " cgRatio > 0.35");
                countCG = countCG+1;
            }
            if (s.length() > longestGene.length()) {
                longestGene = s;
            }
        }
        System.out.println("Strings > 9 is " + count9);
        System.out.println("Strings cgRatio > 0.35 is " + countCG);
        System.out.println("Longest gene is " + longestGene);  
    }
    
    public float cgRatio(String dna) {
        int n = 0;
        int start = 0;               
        while (dna.indexOf("C", start) != -1) {
            n = n+1;
            start = dna.indexOf("C", start)+1;
        }
        start = 0;
        while (dna.indexOf("G", start) != -1) {
            n = n+1;
            start = dna.indexOf("G", start)+1;
        }
        float count = n;
        return count / dna.length();
    }
    
    public void testProcessGenes() {
        StorageResource sr = new StorageResource();
        sr.add("012345678");
        sr.add("AAA");
        sr.add("AAAAAACGCGCG");
        sr.add("CGCG");
        sr.add("AAAAAAAAAAAAACG");
        sr.add("CAAAAAGAA");
        sr.add("AACAAGAAAG");
                       
        processGenes(sr);
        
    }
}
