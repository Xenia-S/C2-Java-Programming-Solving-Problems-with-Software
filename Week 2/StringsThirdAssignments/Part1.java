import edu.duke.*;
import java.io.*;



public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);
        while (currIndex != -1) {
            int geneLength = currIndex - startIndex;
            if (geneLength % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
        
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void printAllGenes(String dna) {
        int startSearchIndex = 0;
        while (true) {
            String gene = findGene(dna, startSearchIndex);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startSearchIndex = dna.indexOf(gene, startSearchIndex) + gene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource genesStorage = new StorageResource();
        int startSearchIndex = 0;
        while (true) {
            String gene = findGene(dna, startSearchIndex);
            if (gene.isEmpty()) {
                break;
            }
            genesStorage.add(gene);
            startSearchIndex = dna.indexOf(gene, startSearchIndex) + gene.length();
            
        }
        return genesStorage;
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
    
    public int countCTG(String dna) {
        int count = 0;
        int start = 0;
        while (dna.indexOf("CTG", start) != -1 ) {
            count = count +1;
            start = dna.indexOf("CTG", start)+1;            
        }
        return count;
    }
    
    public void processGenes(StorageResource sr) {
        int countAll = 0;
        int count60 = 0;
        int count100 = 0;
        int countCG = 0;
        String longestGene = "";
        for (String s : sr.data()) {
            countAll ++;
            if (s.length() > 60) {
                System.out.println(s + " > 60");
                count60 ++;
            }
            if (s.length() > 100) {
                System.out.println(s + " > 100");
                count100 ++;
            }
            if (cgRatio(s) > 0.35) {
                System.out.println(s + " cgRatio > 0.35");
                countCG = countCG+1;
            }
            if (s.length() > longestGene.length()) {
                longestGene = s;
            }
        }
        System.out.println("Genes > 60 is " + count60);
        System.out.println("Genes > 100 is " + count100);
        System.out.println("Genes cgRatio > 0.35 is " + countCG);
        System.out.println("Longest gene is " + longestGene.length() + " " + longestGene);
        System.out.println("Total genes " + countAll);
    }
    
     
    
    
    //TESTS:
    
    public void fingAndProcessGenesFromSr() {
        FileResource fr = new FileResource("brca1line.fa");
        //FileResource fr = new FileResource("DNAtest - Copy.fa");
        String dnaString = fr.asString();
        //dnaString = dnaString.toUpperCase();
        
        StorageResource geneList = getAllGenes(dnaString);
        processGenes(geneList);
        
        int totalCTG = countCTG(dnaString);
        System.out.println("Total CTGs " + totalCTG);
        
    
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
    
    public void testCountCTG() {
        String dna = "AAAAAAAAAAAACTGAAAAA";
        System.out.println(dna);
        System.out.println(countCTG(dna));
        
        dna = "AAAAAAAAAAAACTG";
        System.out.println(dna);
        System.out.println(countCTG(dna));
        
        dna = "CTGAAAAAACTGCTG";
        System.out.println(dna);
        System.out.println(countCTG(dna));
    }
    
    public void testCgRatio() {
        String dna = "AAACAAAAAA";
        System.out.println(dna);
        System.out.println(cgRatio(dna));
        
        dna = "AAACAAAAGA";
        System.out.println(dna);
        System.out.println(cgRatio(dna));
        
        dna = "AAACGAAAAA";
        System.out.println(dna);
        System.out.println(cgRatio(dna));
        
        dna = "CGCGCGCGCG";
        System.out.println(dna);
        System.out.println(cgRatio(dna));
        
        dna = "ATGCCATAG";
        System.out.println(dna);
        System.out.println(cgRatio(dna));
        
    }
    
    
    public void testGetAllGenes() {
        String dna = "aaatttzzzxxxaaatttzzzaaagggaaatttzzzxxxaaatATGttzzzxxxaaTAAatttzTGAzzaaagggaaattTAGtzzzxxxaaatATGttzzzxxxaTAAatttzzTGAzzaaagggaaatTAGtzzzxxxaaatATGttzzzxxxaaTAAatttzzTGAzzaaagggaaatttTAGtzzzxxx";
        StorageResource genes = getAllGenes(dna);
        for (String s : genes.data()) {
            System.out.println("Gene found "+s);
        }
    
    }
    
    public void testFindAllGenes() {
        String dna = "aaatttzzzxxxaaatttzzzaaagggaaatttzzzxxxaaatATGttzzzxxxaaTAAatttzTGAzzaaagggaaattTAGtzzzxxxaaatATGttzzzxxxaTAAatttzzTGAzzaaagggaaatTAGtzzzxxxaaatATGttzzzxxxaaTAAatttzzTGAzzaaagggaaatttTAGtzzzxxx";
        System.out.println(dna);
        printAllGenes(dna);
        System.out.println("");
        
    }
    
    public void testFindGene() {
        String dna = "aaatttzzzxxxaaatttzzzaaagggaaatttzzzxxx";
        String gene = findGene(dna, 0);
        System.out.println(dna);
        System.out.println(gene);

        dna = "aaatATGttzzzxxxaaatttzzzaaagggaaatttzzzxxxNOSTOP";
        gene = findGene(dna, 0);
        System.out.println(dna);
        System.out.println(gene);
        
        dna = "aaatATGttzzzxxxaaTAAatttzTGAzzaaagggaaattTAGtzzzxxx";
        gene = findGene(dna, 0);
        System.out.println(dna);
        System.out.println(gene);
        
        dna = "aaatATGttzzzxxxaTAAatttzzTGAzzaaagggaaatTAGtzzzxxx";
        gene = findGene(dna, 0);
        System.out.println(dna);
        System.out.println(gene);
        
        dna = "aaatATGttzzzxxxaaTAAatttzzTGAzzaaagggaaatttTAGtzzzxxx";
        gene = findGene(dna, 0);
        System.out.println(dna);
        System.out.println(gene);
    }
    
    public void test() {
        String dna = "brjyATGrbflebfTAAvefbvTAAlebvb";
        int testStopCodonIndex = findStopCodon(dna, 4, "TAA");
        System.out.println(testStopCodonIndex);
        
        dna = "jfgATTkjfglrklkrhTAAblkdbkTAAldbkdTAAbflkTAAdflkTAAfkdfkdfbjkdbjkbkjb";
        testStopCodonIndex = findStopCodon(dna, 3, "TAA");
        System.out.println(testStopCodonIndex);
        
        dna = "dgdfbbdbdbdbdbdfdfbdf";
        testStopCodonIndex = findStopCodon(dna, 0, "TAA");
        System.out.println(testStopCodonIndex);
        
        dna = "dgd";
        testStopCodonIndex = findStopCodon(dna, 0, "TAA");
        System.out.println(testStopCodonIndex);
    }
}
