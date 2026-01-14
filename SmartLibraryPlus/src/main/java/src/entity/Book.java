package src.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Veritabanında her kitap için benzersiz bir ID tutuyoruz

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private int year;

    @Enumerated(EnumType.STRING) // Enum değerini veritabanına String olarak (AVAILABLE/BORROWED) kaydediyoruz
    private BookStatus status;

    // Parametresiz constructor (Hibernate için gerekli)
    public Book() {
    }

    // Kitap eklerken kullanacağımız constructor
    public Book(String title, String author, int year, BookStatus status) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = status;
    }

    // Getter ve Setter metodları - encapsulation kuralı gereği fieldlar private,
    // erişim bu metodlarla
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book [ID=" + id + ", Title=" + title + ", Author=" + author + ", Year=" + year + ", Status=" + status
                + "]";
    }
}
