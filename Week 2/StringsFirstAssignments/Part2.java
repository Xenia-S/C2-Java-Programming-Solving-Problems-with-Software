import edu.duke.*;
import java.io.*;

public class Part2 {
   public String findSimpleGene(String dna, String startCodon, String stopCodon) {
       int startIndex = dna.indexOf(startCodon);
       if (startIndex == -1) {
           startIndex = dna.indexOf(startCodon.toLowerCase());
           if (startIndex == -1) {
               return "There is no start Codon";
            }
        }
       int endIndex = dna.indexOf(stopCodon, startIndex+3);
       if (endIndex == -1) {
           endIndex = dna.indexOf(stopCodon.toLowerCase(), startIndex+3);
           if (endIndex == -1) {
               return "There is no stop Codon";
            }  
        }
       if ((endIndex-startIndex) % 3 == 0) {
           return dna.substring(startIndex, endIndex+3);
        }
        else {
            return "There is no gene";
        }
    }
    
   public void testSimpleGene() {
       String startCodon = "ATG";
       String stopCodon = "TAA";
       
       String dna = "TCGATCCGG";
       System.out.println(dna); 
       String gene = findSimpleGene(dna, startCodon, stopCodon);
       System.out.println(gene);
       
       dna = "ACTTTGGCAATGACTTTGGCA";
       System.out.println(dna); 
       gene = findSimpleGene(dna, "ATG", "TAA");
       System.out.println(gene);
       
       dna = "ACTTTGGCAACTTTGGCA";
       System.out.println(dna); 
       gene = findSimpleGene(dna, startCodon, stopCodon);
       System.out.println(gene);
       
       dna = "CCCGGGATGTGTTTACTGCTACACAAGTAA";
       System.out.println(dna); 
       gene = findSimpleGene(dna, startCodon, stopCodon);
       System.out.println(gene);
       
       dna = "CCCGGGATGTGTTTTACTGCTACACAAGTAA";
       System.out.println(dna); 
       gene = findSimpleGene(dna, startCodon, stopCodon);
       System.out.println(gene);
       
       dna = "ATGGGTTAAGTC";
       System.out.println(dna); 
       gene = findSimpleGene(dna, startCodon, stopCodon);
       System.out.println(gene);
       
       dna = "gatgctataat";
       System.out.println(dna); 
       gene = findSimpleGene(dna, startCodon, stopCodon);
       System.out.println(gene);
       
       System.out.println(""); 
    }




} 
