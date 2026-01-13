package library;

public class Book {
    // Статическая переменная для автоинкремента ID
    private static int nextId = 1;
    
    // Поля класса
    private int id;
    private String title;
    private String author;
    private int year;
    private String isbn;
    private boolean available; // true - книга доступна, false - книга выдана

    // Конструктор для создания новой книги
    public Book(String title, String author, int year, String isbn) {
        this.id = nextId; // Присваиваем текущий ID
        nextId++; // Увеличиваем ID для следующей книги
        
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.available = true; // Новая книга всегда доступна
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

    // Метод для вывода информации о книге
    @Override
    public String toString() {
        // Определяем статус книги
        String status;
        if (available) {
            status = "Доступна";
        } else {
            status = "Выдана";
        }
        
        // Форматируем строку с информацией о книге
        return String.format("[ID: %d] \"%s\" ~ %s (%d)\nISBN: %s | Статус: %s", 
            id, title, author, year, isbn, status);
    }
}
