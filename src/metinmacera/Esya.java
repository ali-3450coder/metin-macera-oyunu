package metinmacera;

public abstract class Esya {

    private String kod;        // Komutta kullanılacak id: örn. "anahtar_kirmizi"
    private String ad;         // Gösterilecek isim: "Kırmızı Anahtar"
    private String aciklama;   // Kısa açıklama

    public Esya(String kod, String ad, String aciklama) {
        this.kod = kod;
        this.ad = ad;
        this.aciklama = aciklama;
    }

    public String getKod() {
        return kod;
    }

    public String getAd() {
        return ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    // Her eşya kullanıldığında ne olacağını alt sınıflar belirler
    public abstract void kullan(Oyuncu oyuncu, OyunMotoru baglam);
}
