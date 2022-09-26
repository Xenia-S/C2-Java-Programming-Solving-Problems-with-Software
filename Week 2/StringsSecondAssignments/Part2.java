
public class Part2 {
    public int howMany(String longString, String shortString) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            int currIndex = longString.indexOf(shortString, startIndex);
            if (currIndex == -1) {
                break;
            }
            count = count +1;
            startIndex = currIndex + shortString.length();
        }
        return count;
    }
    
    public void testHowMany() {
        String longString = "ATGAACGAATTGAATC";
        String shortString = "GAA";
        System.out.println(longString);
        System.out.println(shortString);        
        int counts = howMany(longString, shortString);
        System.out.println("I found it "+counts+" times");
        
        longString = "ATAAAA";
        shortString = "AA";
        System.out.println(longString);
        System.out.println(shortString);        
        counts = howMany(longString, shortString);
        System.out.println("I found it "+counts+" times");
        
        longString = "AAAAlkjrnkjnglerfjknkAAjfkrjfslsrsjrljjlsjrjsrjfAA";
        shortString = "AA";
        System.out.println(longString);
        System.out.println(shortString);        
        counts = howMany(longString, shortString);
        System.out.println("I found it "+counts+" times");
        
        longString = "AA";
        shortString = "AA";
        System.out.println(longString);
        System.out.println(shortString);        
        counts = howMany(longString, shortString);
        System.out.println("I found it "+counts+" times");
        
        longString = "вапваивавава";
        shortString = "AA";
        System.out.println(longString);
        System.out.println(shortString);        
        counts = howMany(longString, shortString);
        System.out.println("I found it "+counts+" times");
        
        System.out.println("");
    }

}
