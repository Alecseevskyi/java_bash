package geom;

public class Rectangle {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new RuntimeException("There is no figure with such parameters.");
        }
        this.width = width;
        this.height = height;
    }
    
    public double getArea() {
        return width * height;
    }
    
    public double getPerimeter() {
        return 2 * (width + height);
    }
}
