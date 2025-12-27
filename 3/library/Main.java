package library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library(scanner);
        
        System.out.println("\n" + "~".repeat(60));
        System.out.println("~ Система управления библиотекой ~");
        System.out.println("~".repeat(60));
        System.out.println("   В библиотеке уже есть тестовые книги!");
        System.out.println("   Вы можете начать работу с ними.");
        
        boolean running = true;
        
        while (running) {
            System.out.println("\n~ Главное меню ~");
            System.out.println("   1. Добавить книгу");
            System.out.println("   2. Показать все книги");
            System.out.println("   3. Показать доступные книги");
            System.out.println("   4. Найти книгу");
            System.out.println("   5. Выдать книгу");
            System.out.println("   6. Вернуть книгу");
            System.out.println("   7. Удалить книгу");
            System.out.println("   8. Обновить информацию о книге");
            System.out.println("   9. Показать статистику");
            System.out.println("   10. Показать журнал операций");
            System.out.println("   0. Выход");
            System.out.print("   Выберите действие: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        library.addBook();
                        break;
                        
                    case 2:
                        library.printAllBooks();
                        break;
                        
                    case 3:
                        library.printAvailableBooks();
                        break;
                        
                    case 4:
                        library.searchBooks();
                        break;
                        
                    case 5:
                        library.borrowBook();
                        break;
                        
                    case 6:
                        library.returnBook();
                        break;
                        
                    case 7:
                        library.removeBook();
                        break;
                        
                    case 8:
                        library.updateBook();
                        break;
                        
                    case 9:
                        library.showStatistics();
                        break;
                        
                    case 10:
                        library.printOperationLog();
                        break;
                        
                    case 0:
                        System.out.println("\n" + "~".repeat(60));
                        System.out.println("~ Работа приложения завершена ~");
                        System.out.println("~".repeat(60));
                        running = false;
                        break;
                        
                    default:
                        System.out.println("   Неверный выбор. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("   Ошибка: Введите число");
            } catch (Exception e) {
                System.out.println("   Произошла ошибка: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        scanner.close();
    }
}
