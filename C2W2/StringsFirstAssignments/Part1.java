import edu.duke.*;
import java.io.*;

public class Part1 {
   public String findSimpleGene(String dna) {
       String result = "";
       int startIndex = dna.indexOf("ATG");
       if (startIndex == -1) {
           return "There is no ATG";
        }
       int endIndex = dna.indexOf("TAA", startIndex+3);
       if (endIndex == -1) {
           return "There is no TAA";
        }
       if ((endIndex-startIndex)%3 == 0) {
           return dna.substring(startIndex, endIndex+3);
        }
        else {
            return "There is no gene";
        }
    }
    
   public void testSimpleGene() {
       String dna = "TCGATCCGG";
       System.out.println(dna); 
       String gene = findSimpleGene(dna);
       System.out.println(gene);
       
       dna = "ACTTTGGCAATGACTTTGGCA";
       System.out.println(dna); 
       gene = findSimpleGene(dna);
       System.out.println(gene);
       
       dna = "ACTTTGGCAACTTTGGCA";
       System.out.println(dna); 
       gene = findSimpleGene(dna);
       System.out.println(gene);
       
       dna = "CCCGGGATGTGTTTACTGCTACACAAGTAA";
       System.out.println(dna); 
       gene = findSimpleGene(dna);
       System.out.println(gene);
       
       dna = "CCCGGGATGTGTTTTACTGCTACACAAGTAA";
       System.out.println(dna); 
       gene = findSimpleGene(dna);
       System.out.println(gene);
    }




} 
