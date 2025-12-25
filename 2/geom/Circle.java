package geom;

public class Circle {
    private double radius;
    
    public Circle(double radius) {
        if (radius <= 0) {
            throw new RuntimeException("There is no figure with such parameters.");
        }
        this.radius = radius;
    }
    
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

