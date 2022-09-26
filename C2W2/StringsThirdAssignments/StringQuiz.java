import edu.duke.*;
import java.io.*;

public class StringQuiz {
    public void findYoutubeLinks() { 
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.words()) {
            String s2 = s.toLowerCase();
            if (s2.indexOf("youtube.com") != -1) {
                int string_start = s2.lastIndexOf("\"",s2.indexOf("youtube.com"));
                int string_end = s2.indexOf("\"", s2.indexOf("youtube.com"));
                System.out.println(s.substring(string_start, string_end+1));
            }
        }

    }

    public void testfindYoutubeLinks() {
        String s = "https:\"//www.YouTube.com/watch?v=CPUaTT0Xoo4&list\"=PLYPWr4ErjcnzWB95MVvlKArO6PIfv1fHd";
        String s2 = s.toLowerCase();
            if (s2.indexOf("youtube.com") != -1) {
                int string_start = s2.lastIndexOf("\"", s2.indexOf("youtube.com"));
                int string_end = s2.indexOf("\"", s2.indexOf("youtube.com"));
                System.out.println(string_start);
                System.out.println(string_end);
                System.out.println(s.substring(string_start, string_end+1));
            }
        }
    
    }
        



