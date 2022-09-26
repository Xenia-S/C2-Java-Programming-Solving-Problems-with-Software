import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for (Point currPt : s.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double perim = getPerimeter(s);
        double num = getNumPoints(s);
        double avgLength = perim / num;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()){
            double side = prevPt.distance(currPt);
            if (side > largestSide){
                largestSide = side;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double lx = 0.0;
        for (Point pt : s.getPoints()) {
            double x = pt.getX();
            if (x > lx) {
                lx = x;
            }
        }
        return lx;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double lper = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double per = getPerimeter (s);
            if (per > lper) {
                lper = per;
            }
        }
        return lper;
    }

    public String getFileWithLargestPerimeter() {
        File lfile = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double lper = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double per = getPerimeter (s);
            if (per > lper) {
                lper = per;
                lfile = f;
            }
        }
        return lfile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int pointNum = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double lx = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("points number = " + pointNum);
        System.out.println("average length = " + avgLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest X = " + lx);
    }
    
    public void testPerimeterMultipleFiles() {
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is: " + largest);
    }

    public void testFileWithLargestPerimeter() {
        String file = getFileWithLargestPerimeter();
        System.out.println("Largest perimeter file is: " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
