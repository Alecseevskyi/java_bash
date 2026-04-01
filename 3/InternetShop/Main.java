package InternetShop;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("	Интернет-магазин");
        System.out.println("Добро пожаловать в систему управления корзиной покупок!");
        
        showMenu();
        
        boolean exit = false;
        while (!exit) {
            System.out.print("\nВыберите действие (1-7): ");
            String choice = scanner.nextLine().trim();
            
            try {
                switch (choice) {
                    case "1":
                        createAndAddProduct();
                        break;
                        
                    case "2":
                        removeProductFromCart();
                        break;
                        
                    case "3":
                        cart.printCart();
                        break;
                        
                    case "4":
                        showSampleProducts();
                        break;
                        
                    case "5":
                        clearCart();
                        break;
                        
                    case "6":
                        showStatistics();
                        break;
                        
                    case "7":
                        exit = true;
                        System.out.println("Спасибо за использование! До свидания!");
                        break;
                        
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите действие от 1 до 7.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    private static void showMenu() {
        System.out.println("\n" + "~".repeat(50));
        System.out.println("Меню:");
        System.out.println("~".repeat(50));
        System.out.println("1. Создать товар и добавить в корзину");
        System.out.println("2. Удалить товар из корзины");
        System.out.println("3. Показать корзину и сумму");
        System.out.println("4. Добавить примеры товаров (ноутбук, мышь, клавиатура)");
        System.out.println("5. Очистить корзину");
        System.out.println("6. Показать статистику");
        System.out.println("7. Выход");
        System.out.println("~".repeat(50));
    }
    
    private static void createAndAddProduct() {
        System.out.println("\n	Создание товара");
        
        System.out.print("Введите название товара: ");
        String name = scanner.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("Название товара не может быть пустым");
            return;
        }
        
        double price = 0;
        boolean validPrice = false;
        
        while (!validPrice) {
            System.out.print("Введите цену товара (руб.): ");
            String priceInput = scanner.nextLine().trim();
            
            try {
                price = Double.parseDouble(priceInput);
                if (price < 0) {
                    System.out.println("Цена не может быть отрицательной");
                } else {
                    validPrice = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число для цены");
            }
        }
        
        try {
            Product product = new Product(name, price);
            cart.addProduct(product);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка создания товара: " + e.getMessage());
        }
    }
    
    private static void removeProductFromCart() {
        if (cart.isEmpty()) {
            System.out.println("Корзина пуста. Нечего удалять.");
            return;
        }
        
        cart.printCart();
        System.out.print("\nВведите название товара для удаления: ");
        String productName = scanner.nextLine().trim();
        
        if (productName.isEmpty()) {
            System.out.println("Название товара не может быть пустым");
            return;
        }
        
        cart.removeProduct(productName);
    }
    
    private static void showSampleProducts() {
        System.out.println("\n	Добавление примеров товаров");
        
        Product[] sampleProducts = {
            new Product("Ноутбук", 75000.0),
            new Product("Мышка", 1500.0),
            new Product("Клавиатура", 3000.0),
            new Product("Монитор", 25000.0),
            new Product("Наушники", 5000.0)
        };
        
        System.out.println("Доступные примеры товаров:");
        for (int i = 0; i < sampleProducts.length; i++) {
            System.out.println((i + 1) + ". " + sampleProducts[i]);
        }
        
        System.out.print("\nВыберите номера товаров для добавления (через пробел, например: 1 2 3): ");
        String input = scanner.nextLine().trim();
        
        if (input.isEmpty()) {
            System.out.println("Не выбрано ни одного товара");
            return;
        }
        
        String[] indices = input.split("\\s+");
        int addedCount = 0;
        
        for (String indexStr : indices) {
            try {
                int index = Integer.parseInt(indexStr) - 1;
                if (index >= 0 && index < sampleProducts.length) {
                    cart.addProduct(sampleProducts[index]);
                    addedCount++;
                } else {
                    System.out.println("Неверный номер товара: " + (index + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: '" + indexStr + "' не является числом");
            }
        }
        
        System.out.println("Добавлено товаров: " + addedCount);
        
        if (addedCount > 0) {
            System.out.print("\nПоказать корзину? (да/нет): ");
            String show = scanner.nextLine().trim().toLowerCase();
            if (show.equals("да") || show.equals("д") || show.equals("y") || show.equals("yes")) {
                cart.printCart();
            }
        }
    }
    
    private static void clearCart() {
        if (cart.isEmpty()) {
            System.out.println("Корзина уже пуста");
            return;
        }
        
        System.out.print("Вы уверены, что хотите очистить корзину? (да/нет): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("да") || confirmation.equals("д") || confirmation.equals("y") || confirmation.equals("yes")) {
            cart.clear();
        } else {
            System.out.println("Очистка корзины отменена");
        }
    }
    
    private static void showStatistics() {
        System.out.println("\n	Статистика корзины");
        System.out.println("Количество товаров: " + cart.getItemCount());
        System.out.printf("Общая сумма: %.1f руб.\n", cart.getTotal());
        
        if (!cart.isEmpty()) {
            System.out.println("\nСодержимое корзины:");
            List<Product> products = cart.getProducts();
            double averagePrice = cart.getTotal() / cart.getItemCount();
            System.out.printf("Средняя цена товара: %.1f руб.\n", averagePrice);
            
            Product mostExpensive = null;
            Product cheapest = null;
            
            for (Product product : products) {
                if (mostExpensive == null || product.getPrice() > mostExpensive.getPrice()) {
                    mostExpensive = product;
                }
                if (cheapest == null || product.getPrice() < cheapest.getPrice()) {
                    cheapest = product;
                }
            }
            
            System.out.println("Самый дорогой товар: " + mostExpensive);
            System.out.println("Самый дешевый товар: " + cheapest);
        }
    }
}
