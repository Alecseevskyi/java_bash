package library;

public class Book {
    private static int nextId = 1; // Статическая переменная для автоинкремента
    
    private int id;
    private String title;
    private String author;
    private int year;
    private String isbn;
    private boolean available;

    // Конструктор с автоинкрементом ID
    public Book(String title, String author, int year, String isbn) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.available = true;
    }

    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public int getYear() {
        return year;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        String status = available ? "Доступна" : "Выдана";
        return String.format("[ID: %d] \"%s\" ~ %s (%d)\nISBN: %s | Статус: %s", 
            id, title, author, year, isbn, status);
    }
    
    // Метод для сброса счетчика (для тестирования)
    public static void resetIdCounter() {
        nextId = 1;
    }
}