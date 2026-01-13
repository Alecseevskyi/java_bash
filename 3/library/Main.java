package library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создаем объект Scanner для чтения ввода пользователя
        Scanner scanner = new Scanner(System.in);
        
        // Создаем объект библиотеки
        Library library = new Library(scanner);
        
        System.out.println("\n" + "~".repeat(60));
        System.out.println("~ Система управления библиотекой ~");
        System.out.println("~".repeat(60));
        System.out.println("   Добро пожаловать в библиотеку!");
        System.out.println("   В системе уже есть тестовые книги.");
        
        // Основной цикл программы
        boolean running = true;
        while (running) {
            // Выводим меню
            System.out.println("\n~ Главное меню ~");
            System.out.println("   1. Добавить книгу");
            System.out.println("   2. Показать все книги");
            System.out.println("   3. Показать доступные книги");
            System.out.println("   4. Найти книги по автору");
            System.out.println("   5. Найти книги по названию");
            System.out.println("   6. Выдать книгу");
            System.out.println("   7. Вернуть книгу");
            System.out.println("   8. Удалить книгу");
            System.out.println("   9. Обновить информацию о книге");
            System.out.println("   10. Показать статистику");
            System.out.println("   0. Выход из программы");
            System.out.print("   Выберите действие: ");
            
            // Читаем выбор пользователя
            String choice = scanner.nextLine();
            
            // Обрабатываем выбор пользователя
            switch (choice) {
                case "1":
                    // Добавить книгу
                    library.addBook();
                    break;
                    
                case "2":
                    // Показать все книги
                    library.printAllBooks();
                    break;
                    
                case "3":
                    // Показать доступные книги
                    library.printAvailableBooks();
                    break;
                    
                case "4":
                    // Найти книги по автору
                    library.searchByAuthor();
                    break;
                    
                case "5":
                    // Найти книги по названию
                    library.searchByTitle();
                    break;
                    
                case "6":
                    // Выдать книгу
                    library.borrowBook();
                    break;
                    
                case "7":
                    // Вернуть книгу
                    library.returnBook();
                    break;
                    
                case "8":
                    // Удалить книгу
                    library.removeBook();
                    break;
                    
                case "9":
                    // Обновить информацию о книге
                    library.updateBook();
                    break;
                    
                case "10":
                    // Показать статистику
                    library.showStatistics();
                    break;
                    
                case "0":
                    // Выход из программы
                    System.out.println("\n" + "~".repeat(60));
                    System.out.println("~ Спасибо за использование системы! ~");
                    System.out.println("~".repeat(60));
                    running = false;
                    break;
                    
                default:
                    // Неверный выбор
                    System.out.println("   Неверный выбор. Попробуйте снова.");
            }
        }
        
        // Закрываем Scanner
        scanner.close();
    }
}
