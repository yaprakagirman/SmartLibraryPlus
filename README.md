#  SmartLibraryPlus - ORM Tabanlı Akıllı Kütüphane Sistemi

Bu proje, **Nesneye Yönelik Programlama (OOP)** prensipleri ve **Hibernate ORM** teknolojisi kullanılarak geliştirilmiş, sürdürülebilir bir kütüphane yönetim sistemi simülasyonudur. Proje, veri tutarlılığını sağlamak amacıyla **SQLite** veritabanı üzerinde çalışmaktadır.

---



##  Projenin Amacı ve Kapsamı

Bu çalışmanın temel amacı, ham JDBC kodları yerine **Hibernate Framework** kullanarak veritabanı işlemlerini nesne odaklı bir yapıda yönetmektir. Proje kapsamında aşağıdaki kazanımların uygulanması hedeflenmiştir:

* **ORM (Object Relational Mapping):** Java sınıfları ile veritabanı tablolarının `@Entity` anotasyonları ile eşleştirilmesi.
* **DAO Tasarım Deseni:** Veriye erişim katmanının (Data Access Object) soyutlanması.
* **İlişkisel Veritabanı Yönetimi:** One-to-One ve One-to-Many ilişkilerin Hibernate üzerinden yönetimi.
* **CRUD Operasyonları:** Ekleme, Okuma, Güncelleme ve Silme işlemlerinin `Session` ve `Transaction` yönetimi ile gerçekleştirilmesi.

---

##  Kullanılan Teknolojiler

* **Dil:** Java (JDK 17+)
* **ORM Framework:** Hibernate Core
* **Veritabanı:** SQLite
* **Build Tool:** Maven
* **IDE:** : IntelliJ IDEA 

---

 Uygulama Menüsü ve Özellikler
Uygulama çalıştırıldığında kullanıcıyı aşağıdaki konsol menüsü karşılar:

1 - Kitap Ekle: Sisteme yeni kitap tanımlar (Varsayılan durum: AVAILABLE).

2 - Kitapları Listele: Tüm kitapları ve durumlarını (AVAILABLE/BORROWED) listeler.

3 - Öğrenci Ekle: Sisteme yeni öğrenci kaydeder.

4 - Öğrencileri Listele: Kayıtlı öğrencileri listeler.

5 - Kitap Ödünç Ver: Seçilen öğrenciye, uygun durumdaki bir kitabı ödünç verir. (Kitap durumu BORROWED olarak güncellenir).

6 - Ödünç Listesini Görüntüle: Kimin hangi kitabı ne zaman aldığını listeler.

7 - Kitap Geri Teslim Al: İade tarihini işler ve kitabın durumunu tekrar AVAILABLE yapar.

