package MassStr;

public class StringManagerDemo {
    public static void main(String[] args) {
        StringArrayManager manager = new StringArrayManager(5);
        
        manager.add("one");
        manager.add("two");
        manager.add("three");
        manager.add("four");
        manager.add("five");
        
        System.out.println("Max length element: " + manager.getMaxLengthElement());
        System.out.println("Average length: " + manager.getAverageLength());
        System.out.println("Size: " + manager.getSize());
    }
}
