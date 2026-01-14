package src.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    // Hangi öğrenci ödünç aldı? (ManyToOne)
    // Veritabanında 'student_id' adında bir foreign key kolonu oluşur
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Hangi kitap ödünç alındı? (OneToOne)
    // Bir ödünç işlemi bir kitapla eşleşir
    @OneToOne
    @JoinColumn(name = "book_id", nullable = false, unique = true) // unique=true: Bir kitap aynı anda sadece bir kere
                                                                   // ödünçte olabilir
    private Book book;

    public Loan() {
    }

    public Loan(Student student, Book book, LocalDate borrowDate) {
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
    }

    // Getter ve Setterlar
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Loan [ID=" + id + ", Student=" + student.getName() + ", Book=" + book.getTitle() + ", Date="
                + borrowDate + "]";
    }
}
