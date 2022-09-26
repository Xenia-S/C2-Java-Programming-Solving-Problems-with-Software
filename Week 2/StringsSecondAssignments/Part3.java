
public class Part3 {

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
    
    public int countGenes(String dna) {
        int count = 0;
        int start = 0;
        while (true) {
            String gene = findGene(dna, start);
            if (gene.isEmpty()) {
                break;
            }
            count = count +1;
            start = dna.indexOf(gene, start) + gene.length();
        }
        return count;
    }
    
    public void testCountGenes() {
        //                |  |  |  |  |  |  |  |  |  |  |   |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  
        String dna = "gnkdATGjnTGAgkTAAjfgkTAGjdfgkdjfgkjdfkATGjgdTAGkfgkTGAfgTAAjdfhgdntTGAgrtgATGrtgrgrgTAArtgrbdTAGfbTGAbgfhfgfgrtgggnsATGTAAdfnTGAknd";
        System.out.println(dna);
        System.out.println(countGenes(dna));
                
        //        |  |       |           |  |           |       |  |
        dna = "ATGATGTAAfvdfvATGAAAAATAGaTGAATGdvdfdTGAdTAGdbgfbATGTAAATG";
        System.out.println(dna);
        System.out.println(countGenes(dna));
        
        System.out.println("");
    
    }
}
