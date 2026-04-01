package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    // Список для хранения всех книг в библиотеке
    private List<Book> books;
    // Объект Scanner для чтения ввода пользователя
    private Scanner scanner;
    
    // Конструктор класса Library
    public Library(Scanner scanner) {
        this.books = new ArrayList<>();
        this.scanner = scanner;
        
        // Добавляем тестовые книги при создании библиотеки
        addTestBooks();
    }
    
    // Метод для добавления тестовых книг
    private void addTestBooks() {
        System.out.println("~".repeat(50));
        System.out.println("~ Добавление тестовых книг ~");
        System.out.println("~".repeat(50));
        
        // Создаем несколько тестовых книг
        Book book1 = new Book("Война и мир", "Л.Н. Толстой", 1869, "978-5-17-090335-2");
        Book book2 = new Book("Преступление и наказание", "Ф.М. Достоевский", 1866, "978-5-17-090336-9");
        Book book3 = new Book("Анна Каренина", "Л.Н. Толстой", 1877, "978-5-17-090337-6");
        Book book4 = new Book("Идиот", "Ф.М. Достоевский", 1869, "978-5-17-090338-3");
        Book book5 = new Book("Отцы и дети", "И.С. Тургенев", 1862, "978-5-17-090339-0");
        
        // Добавляем книги в список
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        
        System.out.println("   Добавлено 5 тестовых книг!");
        System.out.println("   ID книг от 1 до 5");
    }
    
    // Метод для добавления новой книги
    public void addBook() {
        System.out.println("\n~ Добавление новой книги ~");
        
        System.out.print("   Введите название книги: ");
        String title = scanner.nextLine();
        
        System.out.print("   Введите автора книги: ");
        String author = scanner.nextLine();
        
        // Проверяем, чтобы год был числом
        int year = 0;
        boolean validYear = false;
        while (!validYear) {
            System.out.print("   Введите год издания: ");
            String yearInput = scanner.nextLine();
            try {
                year = Integer.parseInt(yearInput);
                validYear = true;
            } catch (NumberFormatException e) {
                System.out.println("   Ошибка! Введите число для года.");
            }
        }
        
        System.out.print("   Введите ISBN книги: ");
        String isbn = scanner.nextLine();
        
        // Создаем новую книгу
        Book newBook = new Book(title, author, year, isbn);
        
        // Добавляем книгу в список
        books.add(newBook);
        
        System.out.println("   Книга успешно добавлена!");
        System.out.println("   Присвоен ID: " + newBook.getId());
    }
    
    // Метод для поиска книги по ID
    public Book findBookById(int id) {
        // Проходим по всем книгам в списке
        for (Book book : books) {
            if (book.getId() == id) {
                return book; // Нашли книгу
            }
        }
        return null; // Книга не найдена
    }
    
    // Метод для выдачи книги
    public void borrowBook() {
        System.out.println("\n~ Выдача книги ~");
        
        System.out.print("   Введите ID книги для выдачи: ");
        String input = scanner.nextLine();
        
        try {
            int id = Integer.parseInt(input);
            Book book = findBookById(id);
            
            if (book == null) {
                System.out.println("   Книга с ID " + id + " не найдена!");
                return;
            }
            
            if (!book.isAvailable()) {
                System.out.println("   Книга уже выдана!");
                return;
            }
            
            // Меняем статус книги на "выдана"
            book.setAvailable(false);
            System.out.println("   Книга успешно выдана!");
            
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка! Введите число для ID.");
        }
    }
    
    // Метод для возврата книги
    public void returnBook() {
        System.out.println("\n~ Возврат книги ~");
        
        System.out.print("   Введите ID возвращаемой книги: ");
        String input = scanner.nextLine();
        
        try {
            int id = Integer.parseInt(input);
            Book book = findBookById(id);
            
            if (book == null) {
                System.out.println("   Книга с ID " + id + " не найдена!");
                return;
            }
            
            if (book.isAvailable()) {
                System.out.println("   Книга уже в библиотеке!");
                return;
            }
            
            // Меняем статус книги на "доступна"
            book.setAvailable(true);
            System.out.println("   Книга успешно возвращена!");
            
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка! Введите число для ID.");
        }
    }
    
    // Метод для показа всех книг
    public void printAllBooks() {
        System.out.println("\n~".repeat(40));
        System.out.println("~ Все книги в библиотеке (" + books.size() + ") ~");
        System.out.println("~".repeat(40));
        
        if (books.isEmpty()) {
            System.out.println("   В библиотеке нет книг");
            return;
        }
        
        // Выводим информацию о каждой книге
        for (Book book : books) {
            System.out.println(book);
            System.out.println("-".repeat(40));
        }
    }
    
    // Метод для показа доступных книг
    public void printAvailableBooks() {
        System.out.println("\n~".repeat(40));
        System.out.println("~ Доступные книги ~");
        System.out.println("~".repeat(40));
        
        int availableCount = 0;
        
        // Проходим по всем книгам
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
                System.out.println("-".repeat(40));
                availableCount++;
            }
        }
        
        if (availableCount == 0) {
            System.out.println("   Нет доступных книг");
        } else {
            System.out.println("   Всего доступно: " + availableCount + " книг");
        }
    }
    
    // Метод для поиска книг по автору
    public void searchByAuthor() {
        System.out.println("\n~ Поиск книг по автору ~");
        
        System.out.print("   Введите автора для поиска: ");
        String author = scanner.nextLine();
        
        System.out.println("\n   Найденные книги автора " + author + ":");
        
        boolean found = false;
        
        // Ищем книги указанного автора
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(book);
                System.out.println("-".repeat(40));
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("   Книги этого автора не найдены");
        }
    }
    
    // Метод для поиска книг по названию
    public void searchByTitle() {
        System.out.println("\n~ Поиск книг по названию ~");
        
        System.out.print("   Введите название для поиска: ");
        String title = scanner.nextLine();
        
        System.out.println("\n   Найденные книги с названием \"" + title + "\":");
        
        boolean found = false;
        
        // Ищем книги с указанным названием
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                System.out.println("-".repeat(40));
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("   Книги с таким названием не найдены");
        }
    }
    
    // Метод для удаления книги
    public void removeBook() {
        System.out.println("\n~ Удаление книги ~");
        
        System.out.print("   Введите ID книги для удаления: ");
        String input = scanner.nextLine();
        
        try {
            int id = Integer.parseInt(input);
            Book bookToRemove = null;
            
            // Ищем книгу для удаления
            for (Book book : books) {
                if (book.getId() == id) {
                    bookToRemove = book;
                    break;
                }
            }
            
            if (bookToRemove == null) {
                System.out.println("   Книга с ID " + id + " не найдена!");
                return;
            }
            
            if (!bookToRemove.isAvailable()) {
                System.out.println("   Нельзя удалить выданную книгу!");
                return;
            }
            
            // Удаляем книгу из списка
            books.remove(bookToRemove);
            System.out.println("   Книга успешно удалена!");
            
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка! Введите число для ID.");
        }
    }
    
    // Метод для обновления информации о книге
    public void updateBook() {
        System.out.println("\n~ Обновление информации о книге ~");
        
        System.out.print("   Введите ID книги для обновления: ");
        String input = scanner.nextLine();
        
        try {
            int id = Integer.parseInt(input);
            Book bookToUpdate = findBookById(id);
            
            if (bookToUpdate == null) {
                System.out.println("   Книга с ID " + id + " не найдена!");
                return;
            }
            
            System.out.println("\n   Текущая информация о книге:");
            System.out.println("   " + bookToUpdate);
            
            System.out.println("\n   Введите новые данные:");
            
            System.out.print("   Новое название (оставьте пустым, чтобы не менять): ");
            String newTitle = scanner.nextLine();
            
            System.out.print("   Новый автор (оставьте пустым, чтобы не менять): ");
            String newAuthor = scanner.nextLine();
            
            // Создаем новую книгу с обновленными данными
            String finalTitle = newTitle.isEmpty() ? bookToUpdate.getTitle() : newTitle;
            String finalAuthor = newAuthor.isEmpty() ? bookToUpdate.getAuthor() : newAuthor;
            
            // Сохраняем статус книги
            boolean wasAvailable = bookToUpdate.isAvailable();
            
            // Удаляем старую книгу
            books.remove(bookToUpdate);
            
            // Создаем новую книгу с теми же данными, но возможно измененными названием и автором
            // Для простоты используем те же год и ISBN
            Book updatedBook = new Book(finalTitle, finalAuthor, bookToUpdate.getYear(), bookToUpdate.getIsbn()) {
                // Переопределяем метод getId, чтобы сохранить старый ID
                @Override
                public int getId() {
                    return id;
                }
            };
            
            // Восстанавливаем статус доступности
            updatedBook.setAvailable(wasAvailable);
            
            // Добавляем обновленную книгу
            books.add(updatedBook);
            
            System.out.println("   Информация о книге успешно обновлена!");
            
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка! Введите число для ID.");
        }
    }
    
    // Метод для показа статистики
    public void showStatistics() {
        System.out.println("\n~".repeat(40));
        System.out.println("~ Статистика библиотеки ~");
        System.out.println("~".repeat(40));
        
        int totalBooks = books.size();
        int availableBooks = 0;
        int borrowedBooks = 0;
        
        // Считаем доступные и выданные книги
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks++;
            } else {
                borrowedBooks++;
            }
        }
        
        System.out.println("   Всего книг: " + totalBooks);
        System.out.println("   Доступно: " + availableBooks);
        System.out.println("   Выдано: " + borrowedBooks);
    }
}
