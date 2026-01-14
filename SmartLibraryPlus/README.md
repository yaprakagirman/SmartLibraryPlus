# SmartLibraryPlus - Akıllı Kütüphane Sistemi

Bu proje, **Object Oriented Programming (OOP)** dersi final ödevi kapsamında geliştirilmiştir. Kütüphane yönetimini otomatize etmek amacıyla Java, Hibernate ve SQLite teknolojileri kullanılarak tasarlanmıştır.

## 🚀 Proje Hakkında
SmartLibraryPlus, kütüphanedeki kitapların ve öğrencilerin takibini sağlayan bir konsol uygulamasıdır. Kitap ödünç verme ve iade alma işlemleri veritabanı üzerinde tutarlı bir şekilde yönetilir.

## 🛠 Kullanılan Teknolojiler
- **Java 17**: Programlama dili.
- **Hibernate ORM (6.x)**: Veritabanı ile nesne-ilişkisel eşleme (ORM) için.
- **SQLite**: Hafif ve pratik veritabanı çözümü.
- **Maven**: Proje bağımlılık yönetimi.

## 📂 Proje Yapısı
- `src/entity`: Veritabanı tablolarına karşılık gelen sınıflar (`Book`, `Student`, `Loan`).
- `src/dao`: Veritabanı işlemlerini yapan sınıflar (Data Access Objects).
- `src/util`: Helper sınıfları (`HibernateUtil`).
- `src/app`: Uygulamanın çalıştığı ana sınıf (`App.java`).

## ⚙️ Kurulum ve Çalıştırma

1. Projeyi bir IDE (IntelliJ IDEA veya Eclipse) ile açın.
2. Maven bağımlılıklarının yüklenmesini bekleyin (`pom.xml` üzerinden otomatik yüklenecektir).
3. `src/main/resources/hibernate.cfg.xml` dosyasındaki ayarları kontrol edin (Varsayılan olarak `smartlibrary.db` oluşturur).
4. `src/app/App.java` dosyasını çalıştırın.
5. Konsol menüsünü kullanarak işlemleri yapabilirsiniz.

## 📝 Örnek Senaryo
1. Menüden **1**'i seçerek "Nutuk" kitabını ekleyin.
2. Menüden **3**'ü seçerek "Ali Veli" adında bir öğrenci ekleyin.
3. Menüden **5**'i seçerek kitabı öğrenciye ödünç verin. Veritabanında `Loan` kaydı oluşacak ve kitabın durumu `BORROWED` olacaktır.
4. Menüden **6**'yı seçerek kitabı iade alın. `return_date` güncellenecek ve kitap tekrar `AVAILABLE` olacaktır.

## ⚠️ Notlar
- Veritabanı dosyası proje klasöründe `smartlibrary.db` olarak oluşur.
- İlk çalıştırmada Hibernate tabloları `hibernate.cfg.xml`deki `hbm2ddl.auto=update` ayarı sayesinde otomatik oluşturur.
