package InternetShop;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;
    
    public Cart() {
        products = new ArrayList<>();
    }
    
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }
        
        // Проверяем, есть ли уже такой товар в корзине
        for (Product p : products) {
            if (p.equals(product)) {
                System.out.println("Товар '" + product.getName() + "' уже есть в корзине");
                return;
            }
        }
        
        products.add(product);
        System.out.println("Товар '" + product.getName() + "' добавлен в корзину");
    }
    
    public boolean removeProduct(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            System.out.println("Название товара не может быть пустым");
            return false;
        }
        
        String name = productName.trim();
        boolean removed = products.removeIf(p -> p.getName().equalsIgnoreCase(name));
        
        if (removed) {
            System.out.println("Товар '" + name + "' удален из корзины");
        } else {
            System.out.println("Товар '" + name + "' не найден в корзине");
        }
        
        return removed;
    }
    
    public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
    
    public void printCart() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Корзина покупок");
        System.out.println("=".repeat(50));
        
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i));
            }
            System.out.println("-".repeat(50));
            System.out.printf("Итого: %.1f руб.\n", getTotal());
        }
        System.out.println("=".repeat(50));
    }
    
    public boolean isEmpty() {
        return products.isEmpty();
    }
    
    public int getItemCount() {
        return products.size();
    }
    
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
    
    public void clear() {
        products.clear();
        System.out.println("Корзина очищена");
    }
}
