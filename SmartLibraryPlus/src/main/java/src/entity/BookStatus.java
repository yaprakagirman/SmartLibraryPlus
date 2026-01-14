package src.entity;

// Kitabın durumunu belirten Enum yapısı
// Enum kullanmak string karşılaştırmalarından daha güvenlidir
public enum BookStatus {
    AVAILABLE,  // Kitap kütüphanede, ödünç alınabilir
    BORROWED    // Kitap ödünç verilmiş
}
