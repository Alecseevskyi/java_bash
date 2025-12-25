package MassStr;

public class StringArrayManager {
    private String[] array;
    private int size;
    private int capacity;
    
    public StringArrayManager(int initialCapacity) {
        this.capacity = initialCapacity;
        this.array = new String[capacity];
        this.size = 0;
    }
    
    public void add(String str) {
        if (size == capacity) {
            resize();
        }
        
        int insertIndex = size;
        for (int i = 0; i < size; i++) {
            if (str.length() < array[i].length()) {
                insertIndex = i;
                break;
            }
        }
        
        for (int i = size; i > insertIndex; i--) {
            array[i] = array[i - 1];
        }
        
        array[insertIndex] = str;
        size++;
    }
    
    public String getMaxLengthElement() {
        if (size == 0) {
            return null;
        }
        return array[size - 1];
    }
    
    public double getAverageLength() {
        if (size == 0) {
            return 0.0;
        }
        
        int totalLength = 0;
        for (int i = 0; i < size; i++) {
            totalLength += array[i].length();
        }
        
        return (double) totalLength / size;
    }
    
    private void resize() {
        capacity *= 2;
        String[] newArray = new String[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
    
    public int getSize() {
        return size;
    }
}