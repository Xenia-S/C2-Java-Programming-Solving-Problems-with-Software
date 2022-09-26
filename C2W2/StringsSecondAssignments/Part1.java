
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
            //startIndex = startIndex + gene.length();
            startSearchIndex = dna.indexOf(gene, startSearchIndex) + gene.length();
            System.out.println(startSearchIndex);
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
        
        //         |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  
        dna = "aaatATGttzzzxxxaaatttzzzaaagggaaatttzzzxxxNOSTOP";
        gene = findGene(dna, 0);
        System.out.println(dna);
        System.out.println(gene);
        
        //         |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  
        dna = "aaatATGttzzzxxxaaTAAatttzTGAzzaaagggaaattTAGtzzzxxx";
        gene = findGene(dna, 0);
        System.out.println(dna);
        System.out.println(gene);
        
        //         |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  
        dna = "aaatATGttzzzxxxaTAAatttzzTGAzzaaagggaaatTAGtzzzxxx";
        gene = findGene(dna, 0);
        System.out.println(dna);
        System.out.println(gene);
        
        //         |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  
        dna = "aaatATGttzzzxxxaaTAAatttzzTGAzzaaagggaaatttTAGtzzzxxx";
        gene = findGene(dna, 0);
        System.out.println(dna);
        System.out.println(gene);
    }
    
    public void test() {
        //            012345678901234567890123456789012
        //                |  |  |  |  |  |  |  |  |
        String dna = "brjyATGrbflebfTAAvefbvTAAlebvb";
        int testStopCodonIndex = findStopCodon(dna, 4, "TAA");
        System.out.println(testStopCodonIndex);
        
        //     0123456789012345678901234567890123456789012345678901234567890123456789
        //        |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  
        dna = "jfgATTkjfglrklkrhTAAblkdbkTAAldbkdTAAbflkTAAdflkTAAfkdfkdfbjkdbjkbkjb";
        testStopCodonIndex = findStopCodon(dna, 3, "TAA");
        System.out.println(testStopCodonIndex);
        
        //     012345678901234567890
        dna = "dgdfbbdbdbdbdbdfdfbdf";
        testStopCodonIndex = findStopCodon(dna, 0, "TAA");
        System.out.println(testStopCodonIndex);
        
        //     012345678901234567890
        dna = "dgd";
        testStopCodonIndex = findStopCodon(dna, 0, "TAA");
        System.out.println(testStopCodonIndex);
    }
    
}
