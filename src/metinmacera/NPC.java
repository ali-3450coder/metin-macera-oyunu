package metinmacera;

public abstract class NPC {

    private String kod;   // komutta kullanılacak: "muhafiz"
    private String ad;    // gösterilecek isim: "Muhafız"

    public NPC(String kod, String ad) {
        this.kod = kod;
        this.ad = ad;
    }

    public String getKod() {
        return kod;
    }

    public String getAd() {
        return ad;
    }
}
