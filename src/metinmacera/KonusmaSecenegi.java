package metinmacera;

public class KonusmaSecenegi {

    private String metin;
    private KonusmaDugumu sonraki;

    public KonusmaSecenegi(String metin, KonusmaDugumu sonraki) {
        this.metin = metin;
        this.sonraki = sonraki;
    }

    public String getMetin() {
        return metin;
    }

    public KonusmaDugumu getSonraki() {
        return sonraki;
    }
}
