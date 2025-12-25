package zad1;

import java.util.Scanner;

public class MaxFinder {
    
    public static int findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }
        
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        int[] testArray = {3, 7, 2, 9, 5};
        System.out.println("Максимальное значение: " + findMax(testArray));
    }
}
