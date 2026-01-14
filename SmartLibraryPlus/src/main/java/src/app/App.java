package src.app;

import src.dao.BookDAO;
import src.dao.LoanDAO;
import src.dao.StudentDAO;
import src.entity.Book;
import src.entity.BookStatus;
import src.entity.Loan;
import src.entity.Student;
import src.util.HibernateUtil; // SessionFactory'i başlatmak için

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BookDAO bookDAO = new BookDAO();
    private static final StudentDAO studentDAO = new StudentDAO();
    private static final LoanDAO loanDAO = new LoanDAO();

    public static void main(String[] args) {
        // Uygulama başlarken Hibernate'i hazırlıyoruz (itiraf: ilk açılış biraz yavaş
        // olabilir)
        HibernateUtil.getSessionFactory();

        while (true) {
            System.out.println("\n=== SmartLibraryPlus Yönetim Paneli ===");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitapları Listele");
            System.out.println("3. Öğrenci Ekle");
            System.out.println("4. Öğrencileri Listele");
            System.out.println("5. Kitap Ödünç Ver");
            System.out.println("6. Kitap İade Al");
            System.out.println("7. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı giriniz.");
                continue;
            }

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    System.out.println("Programdan çıkılıyor. Güle güle!");
                    // Program kapanırken connection havuzunu da kapatmayı unutmuyoruz
                    HibernateUtil.getSessionFactory().close();
                    return;
                default:
                    System.out.println("Geçersiz seçim, tekrar deneyiniz.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Kitap Adı: ");
        String title = scanner.nextLine();
        System.out.print("Yazar: ");
        String author = scanner.nextLine();
        System.out.print("Yıl: ");
        int year = Integer.parseInt(scanner.nextLine());

        Book book = new Book(title, author, year, BookStatus.AVAILABLE);
        bookDAO.saveBook(book);
        System.out.println("Kitap başarıyla eklendi.");
    }

    private static void listBooks() {
        List<Book> books = bookDAO.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Kayıtlı kitap bulunamadı.");
        } else {
            System.out.println("\n--- Kitap Listesi ---");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void addStudent() {
        System.out.print("Öğrenci Adı: ");
        String name = scanner.nextLine();
        System.out.print("Bölüm: ");
        String department = scanner.nextLine();

        Student student = new Student(name, department);
        studentDAO.saveStudent(student);
        System.out.println("Öğrenci başarıyla eklendi.");
    }

    private static void listStudents() {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("Kayıtlı öğrenci bulunamadı.");
        } else {
            System.out.println("\n--- Öğrenci Listesi ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private static void borrowBook() {
        System.out.print("Ödünç Verilecek Kitap ID: ");
        Long bookId = Long.parseLong(scanner.nextLine());
        Book book = bookDAO.getBookById(bookId);

        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        // Kitap zaten ödünçteyse işlem yapmıyoruz
        if (book.getStatus() == BookStatus.BORROWED) {
            System.out.println("Bu kitap şu an başkasında (BORROWED).");
            return;
        }

        System.out.print("Öğrenci ID: ");
        Long studentId = Long.parseLong(scanner.nextLine());
        Student student = studentDAO.getStudentById(studentId);

        if (student == null) {
            System.out.println("Öğrenci bulunamadı.");
            return;
        }

        // Ödünç işlemini oluşturuyoruz
        Loan loan = new Loan(student, book, LocalDate.now());
        loanDAO.saveLoan(loan);

        // Kitabın durumunu güncelliyoruz
        book.setStatus(BookStatus.BORROWED);
        bookDAO.updateBook(book);

        System.out.println("Kitap öğrenciye ödünç verildi.");
    }

    private static void returnBook() {
        System.out.print("İade Edilecek Kitap ID: ");
        Long bookId = Long.parseLong(scanner.nextLine());
        Book book = bookDAO.getBookById(bookId);

        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        if (book.getStatus() == BookStatus.AVAILABLE) {
            System.out.println("Bu kitap zaten kütüphanede (AVAILABLE).");
            return;
        }

        // Kitabın aktif loan kaydını bulmamız lazım.
        // Basitlik adina tüm loanları gezip bu kitaba ait ve returnDate'i null olanı
        // bulabiliriz
        // Ya da LoanDAO'ya özel bir metod ekleyebilirdik, burada loop ile yapalım
        List<Loan> loans = loanDAO.getAllLoans();
        Loan activeLoan = null;
        for (Loan l : loans) {
            if (l.getBook().getId().equals(bookId) && l.getReturnDate() == null) {
                activeLoan = l;
                break;
            }
        }

        if (activeLoan != null) {
            activeLoan.setReturnDate(LocalDate.now());
            loanDAO.updateLoan(activeLoan);

            book.setStatus(BookStatus.AVAILABLE);
            bookDAO.updateBook(book);

            System.out.println("Kitap iade alındı.");
        } else {
            System.out.println("Hata: Bu kitap için aktif bir ödünç kaydı bulunamadı (Veri tutarsızlığı olabilir).");
            // Yine de kitabı müsait yapalım
            book.setStatus(BookStatus.AVAILABLE);
            bookDAO.updateBook(book);
        }
    }
}
