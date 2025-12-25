package square;

public class IntegralCalculator {
    
    public static double function(double x) {
        return x * x * x + x + 1;
    }
    
    public static double rectangleMethod(double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.0;
        
        for (int i = 0; i < n; i++) {
            double x = a + i * h;
            sum += function(x);
        }
        
        return sum * h;
    }
    
    public static void main(String[] args) {
        double a = 0.0;
        double b = 2.0;
        int n = 1000;
        
        double area = rectangleMethod(a, b, n);
        System.out.println("Площадь под графиком: " + area);
    }
}