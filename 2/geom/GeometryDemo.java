package geom;

public class GeometryDemo {
    public static void main(String[] args) {
        try {
            Rectangle rect = new Rectangle(5, 10);
            System.out.println("Rectangle area: " + rect.getArea());
            System.out.println("Rectangle perimeter: " + rect.getPerimeter());
            
            Circle circle = new Circle(3);
            System.out.println("Circle area: " + circle.getArea());
            System.out.println("Circle perimeter: " + circle.getPerimeter());
            
            Triangle triangle = new Triangle(3, 4, 5);
            System.out.println("Triangle area: " + triangle.getArea());
            System.out.println("Triangle perimeter: " + triangle.getPerimeter());
            
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
