package src.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import src.entity.Book;
import src.entity.Loan;
import src.entity.Student;

public class HibernateUtil {
    // SessionFactory nesnesi uygulama boyunca tek bir defa oluşturulmalı (Singleton Pattern)
    private static SessionFactory sessionFactory;

    // SessionFactory'yi oluşturan ve döndüren metod
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Konfigürasyon dosyasını okuyup veritabanı bağlantı ayarlarını alıyoruz
                Configuration configuration = new Configuration();
                configuration.configure();
                
                // Entity sınıflarımızı Hibernate'e tanıtıyoruz
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Loan.class);
                
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                // Hata durumunda konsola bilgi veriyoruz, bu kısım debug için önemli
                System.out.println("SessionFactory oluşturulurken hata oluştu: " + e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
