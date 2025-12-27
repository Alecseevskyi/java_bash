package library;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private OperationLog operationLog;
    private Scanner scanner;
    
    public Library(Scanner scanner) {
        this.books = new ArrayList<>();
        this.operationLog = new OperationLog();
        this.scanner = scanner;
        addTestBooks(); // Добавляем тестовые книги при создании библиотеки
    }
    
    // Добавление тестовых книг
    private void addTestBooks() {
        Book.resetIdCounter(); // Сбрасываем счетчик для тестовых книг
        
        // Список тестовых книг
        String[][] testBooks = {
            {"Война и мир", "Л.Н. Толстой", "1869", "978-5-17-090335-2"},
            {"Преступление и наказание", "Ф.М. Достоевский", "1866", "978-5-17-090336-9"},
            {"Анна Каренина", "Л.Н. Толстой", "1877", "978-5-17-090337-6"},
            {"Идиот", "Ф.М. Достоевский", "1869", "978-5-17-090338-3"},
            {"Отцы и дети", "И.С. Тургенев", "1862", "978-5-17-090339-0"},
            {"Мастер и Маргарита", "М.А. Булгаков", "1967", "978-5-17-090340-6"},
            {"Евгений Онегин", "А.С. Пушкин", "1833", "978-5-17-090341-3"}
        };
        
        for (String[] bookData : testBooks) {
            String title = bookData[0];
            String author = bookData[1];
            int year = Integer.parseInt(bookData[2]);
            String isbn = bookData[3];
            
            Book book = new Book(title, author, year, isbn);
            books.add(book);
            operationLog.addEntry(OperationLog.OperationType.ADD_BOOK, 
                "Добавлена тестовая книга: \"" + title + "\" (ID: " + book.getId() + ")");
        }
        
        System.out.println("~".repeat(50));
        System.out.println("~ Добавлено " + testBooks.length + " тестовых книг ~");
        System.out.println("~".repeat(50));
    }
    
    // Вложенный статический класс для журнала операций
    public static class OperationLog {
        public class LogEntry {
            private OperationType type;
            private LocalDateTime timestamp;
            private String description;
            
            public LogEntry(OperationType type, String description) {
                this.type = type;
                this.timestamp = LocalDateTime.now();
                this.description = description;
            }
            
            public OperationType getType() {
                return type;
            }
            
            public LocalDateTime getTimestamp() {
                return timestamp;
            }
            
            public String getDescription() {
                return description;
            }
            
            @Override
            public String toString() {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                return String.format("~ [%s] %s ~ %s", 
                    timestamp.format(formatter), type, description);
            }
        }
        
        public enum OperationType {
            ADD_BOOK("Добавление книги"),
            BORROW("Выдача книги"),
            RETURN("Возврат книги"),
            REMOVE("Удаление книги"),
            UPDATE("Обновление книги");
            
            private final String description;
            
            OperationType(String description) {
                this.description = description;
            }
            
            @Override
            public String toString() {
                return description;
            }
        }
        
        private List<LogEntry> entries;
        
        public OperationLog() {
            this.entries = new ArrayList<>();
        }
        
        public void addEntry(OperationType type, String description) {
            entries.add(new LogEntry(type, description));
        }
        
        public List<LogEntry> getEntries() {
            return new ArrayList<>(entries);
        }
        
        public void printLog() {
            if (entries.isEmpty()) {
                System.out.println("   Журнал операций пуст");
                return;
            }
            
            System.out.println("\n" + "~".repeat(50));
            System.out.println("~ Журнал операций библиотеки ~");
            System.out.println("~".repeat(50));
            
            for (int i = 0; i < entries.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, entries.get(i));
            }
            
            System.out.println("~".repeat(50) + "\n");
        }
    }
    
    // Методы библиотеки
    public void addBook() {
        System.out.println("\n~ Добавление новой книги ~");
        
        System.out.print("   Введите название книги: ");
        String title = scanner.nextLine();
        
        if (title.trim().isEmpty()) {
            System.out.println("   Ошибка: Название не может быть пустым!");
            return;
        }
        
        System.out.print("   Введите автора: ");
        String author = scanner.nextLine();
        
        if (author.trim().isEmpty()) {
            System.out.println("   Ошибка: Автор не может быть пустым!");
            return;
        }
        
        int year;
        try {
            System.out.print("   Введите год издания: ");
            year = Integer.parseInt(scanner.nextLine());
            if (year < 0 || year > 2100) {
                System.out.println("   Ошибка: Неверный год!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка: Введите корректный год!");
            return;
        }
        
        System.out.print("   Введите ISBN: ");
        String isbn = scanner.nextLine();
        
        Book book = new Book(title, author, year, isbn);
        books.add(book);
        operationLog.addEntry(OperationLog.OperationType.ADD_BOOK, 
            "Добавлена: \"" + title + "\" (ID: " + book.getId() + ")");
        System.out.println("   Книга успешно добавлена! Присвоен ID: " + book.getId());
    }
    
    public Book findBookById(int id) {
        return books.stream()
            .filter(book -> book.getId() == id)
            .findFirst()
            .orElse(null);
    }
    
    public void searchBooks() {
        System.out.println("\n~ Поиск книг ~");
        System.out.println("   1. По ID");
        System.out.println("   2. По автору");
        System.out.println("   3. По названию");
        System.out.print("   Выберите вариант поиска: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    System.out.print("   Введите ID книги: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Book book = findBookById(id);
                    if (book != null) {
                        System.out.println("\n   Найдена книга:");
                        System.out.println("   " + book);
                    } else {
                        System.out.println("   Книга с ID " + id + " не найдена");
                    }
                    break;
                    
                case 2:
                    System.out.print("   Введите автора: ");
                    String author = scanner.nextLine();
                    List<Book> authorBooks = findBooksByAuthor(author);
                    printBookList("Книги автора " + author, authorBooks);
                    break;
                    
                case 3:
                    System.out.print("   Введите название или часть названия: ");
                    String title = scanner.nextLine();
                    List<Book> titleBooks = findBooksByTitle(title);
                    printBookList("Книги по запросу \"" + title + "\"", titleBooks);
                    break;
                    
                default:
                    System.out.println("   Неверный выбор");
            }
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка: Введите число");
        }
    }
    
    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
            .filter(book -> book.getAuthor().equalsIgnoreCase(author))
            .collect(Collectors.toList());
    }
    
    public List<Book> findBooksByTitle(String title) {
        return books.stream()
            .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    public void borrowBook() {
        System.out.println("\n~ Выдача книги ~");
        printAvailableBooks();
        
        if (getAvailableBooks().isEmpty()) {
            System.out.println("   Нет доступных книг для выдачи");
            return;
        }
        
        System.out.print("   Введите ID книги для выдачи: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book book = findBookById(id);
            
            if (book == null) {
                System.out.println("   Ошибка: Книга с ID " + id + " не найдена!");
                return;
            }
            
            if (!book.isAvailable()) {
                System.out.println("   Ошибка: Книга уже выдана!");
                return;
            }
            
            book.setAvailable(false);
            operationLog.addEntry(OperationLog.OperationType.BORROW, 
                "Выдана: \"" + book.getTitle() + "\" (ID: " + id + ")");
            System.out.println("   Книга успешно выдана!");
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка: Введите корректный ID");
        }
    }
    
    public void returnBook() {
        System.out.println("\n~ Возврат книги ~");
        
        // Показываем выданные книги
        List<Book> borrowedBooks = books.stream()
            .filter(book -> !book.isAvailable())
            .collect(Collectors.toList());
        
        if (borrowedBooks.isEmpty()) {
            System.out.println("   Нет выданных книг");
            return;
        }
        
        System.out.println("   Выданные книги:");
        for (Book book : borrowedBooks) {
            System.out.println("   ID: " + book.getId() + " - \"" + book.getTitle() + "\"");
        }
        
        System.out.print("   Введите ID возвращаемой книги: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book book = findBookById(id);
            
            if (book == null) {
                System.out.println("   Ошибка: Книга с ID " + id + " не найдена!");
                return;
            }
            
            if (book.isAvailable()) {
                System.out.println("   Ошибка: Книга уже в библиотеке!");
                return;
            }
            
            book.setAvailable(true);
            operationLog.addEntry(OperationLog.OperationType.RETURN, 
                "Возвращена: \"" + book.getTitle() + "\" (ID: " + id + ")");
            System.out.println("   Книга успешно возвращена!");
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка: Введите корректный ID");
        }
    }
    
    public List<Book> getAvailableBooks() {
        return books.stream()
            .filter(Book::isAvailable)
            .collect(Collectors.toList());
    }
    
    public void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("   В библиотеке нет книг");
            return;
        }
        
        System.out.println("\n" + "~".repeat(60));
        System.out.println("~ Каталог библиотеки (" + books.size() + " книг) ~");
        System.out.println("~".repeat(60));
        
        for (Book book : books) {
            System.out.println(book);
            System.out.println("~".repeat(40));
        }
    }
    
    public void printAvailableBooks() {
        List<Book> available = getAvailableBooks();
        
        if (available.isEmpty()) {
            System.out.println("   Нет доступных книг");
            return;
        }
        
        System.out.println("\n" + "~".repeat(50));
        System.out.println("~ Доступные книги (" + available.size() + " из " + books.size() + ") ~");
        System.out.println("~".repeat(50));
        
        for (Book book : available) {
            System.out.println(book);
        }
    }
    
    public void printOperationLog() {
        operationLog.printLog();
    }
    
    public void showStatistics() {
        System.out.println("\n" + "~".repeat(40));
        System.out.println("~ Статистика библиотеки ~");
        System.out.println("~".repeat(40));
        
        int total = books.size();
        long available = books.stream().filter(Book::isAvailable).count();
        long borrowed = total - available;
        
        System.out.println("   Всего книг: " + total);
        System.out.println("   Доступно: " + available);
        System.out.println("   Выдано: " + borrowed);
        System.out.println("~".repeat(40));
    }
    
    public void removeBook() {
        System.out.println("\n~ Удаление книги ~");
        printAllBooks();
        
        System.out.print("   Введите ID книги для удаления: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book book = findBookById(id);
            
            if (book == null) {
                System.out.println("   Ошибка: Книга с ID " + id + " не найдена!");
                return;
            }
            
            if (!book.isAvailable()) {
                System.out.println("   Ошибка: Нельзя удалить выданную книгу!");
                return;
            }
            
            System.out.print("   Вы уверены, что хотите удалить книгу \"" + book.getTitle() + "\"? (да/нет): ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("да")) {
                books.remove(book);
                operationLog.addEntry(OperationLog.OperationType.REMOVE, 
                    "Удалена: \"" + book.getTitle() + "\" (ID: " + id + ")");
                System.out.println("   Книга успешно удалена!");
            } else {
                System.out.println("   Удаление отменено");
            }
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка: Введите корректный ID");
        }
    }
    
    public void updateBook() {
        System.out.println("\n~ Обновление информации о книге ~");
        printAllBooks();
        
        System.out.print("   Введите ID книги для обновления: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Book book = findBookById(id);
            
            if (book == null) {
                System.out.println("   Ошибка: Книга с ID " + id + " не найдена!");
                return;
            }
            
            System.out.println("\n   Текущая информация о книге:");
            System.out.println("   " + book);
            
            System.out.println("\n   Введите новые данные (оставьте пустым, чтобы не менять):");
            
            System.out.print("   Новое название: ");
            String newTitle = scanner.nextLine();
            
            System.out.print("   Новый автор: ");
            String newAuthor = scanner.nextLine();
            
            System.out.print("   Новый год издания: ");
            String yearInput = scanner.nextLine();
            Integer newYear = null;
            if (!yearInput.isEmpty()) {
                try {
                    newYear = Integer.parseInt(yearInput);
                    if (newYear < 0 || newYear > 2100) {
                        System.out.println("   Ошибка: Неверный год!");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("   Ошибка: Введите корректный год!");
                    return;
                }
            }
            
            System.out.print("   Новый ISBN: ");
            String newIsbn = scanner.nextLine();
            
            // Обновляем поля книги
            if (!newTitle.isEmpty()) {
                // Создаем новый объект с обновленным названием (но ID остается прежним)
                // В реальном приложении здесь был бы сеттер для title
                // Для этого задания используем рефлексию или создадим класс-обертку
                // Упрощенный вариант: создаем новую книгу и заменяем старую
                books.remove(book);
                Book updatedBook = new Book(
                    newTitle.isEmpty() ? book.getTitle() : newTitle,
                    newAuthor.isEmpty() ? book.getAuthor() : newAuthor,
                    newYear == null ? book.getYear() : newYear,
                    newIsbn.isEmpty() ? book.getIsbn() : newIsbn
                ) {
                    // Анонимный класс для сохранения старого ID
                    @Override
                    public int getId() {
                        return id; // Сохраняем оригинальный ID
                    }
                };
                updatedBook.setAvailable(book.isAvailable());
                books.add(updatedBook);
                
                operationLog.addEntry(OperationLog.OperationType.UPDATE, 
                    "Обновлена: \"" + updatedBook.getTitle() + "\" (ID: " + id + ")");
                System.out.println("   Информация о книге успешно обновлена!");
            }
        } catch (NumberFormatException e) {
            System.out.println("   Ошибка: Введите корректный ID");
        }
    }
    
    private void printBookList(String title, List<Book> bookList) {
        if (bookList.isEmpty()) {
            System.out.println("   Книги не найдены");
            return;
        }
        
        System.out.println("\n" + "~".repeat(50));
        System.out.println("~ " + title + " (" + bookList.size() + " книг) ~");
        System.out.println("~".repeat(50));
        
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
}
