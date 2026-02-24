package metinmacera;

import java.util.ArrayList;
import java.util.List;

public class KonusmaDugumu {

    private String metin;
    private List<KonusmaSecenegi> secenekler;

    public KonusmaDugumu(String metin) {
        this.metin = metin;
        this.secenekler = new ArrayList<>();
    }

    public String getMetin() {
        return metin;
    }

    public List<KonusmaSecenegi> getSecenekler() {
        return secenekler;
    }

    public void secenekEkle(KonusmaSecenegi secenek) {
        secenekler.add(secenek);
    }

    public boolean yaprakMi() {
        return secenekler.isEmpty();
    }
}
