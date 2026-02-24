package metinmacera;

public class EsyaIksir extends Esya {

    private int iyilestirmeMiktari;

    public EsyaIksir(String kod, String ad, String aciklama, int iyilestirmeMiktari) {
        super(kod, ad, aciklama);
        this.iyilestirmeMiktari = iyilestirmeMiktari;
    }

    public int getIyilestirmeMiktari() {
        return iyilestirmeMiktari;
    }

    @Override
    public void kullan(Oyuncu oyuncu, OyunMotoru baglam) {
        int eskiCan = oyuncu.getCan();
        int yeniCan = eskiCan + iyilestirmeMiktari;

        if (yeniCan > 100) {
            yeniCan = 100;
        }

        oyuncu.setCan(yeniCan);

        System.out.println(getAd() + " içtin.");
        System.out.println("Canın " + eskiCan + " → " + yeniCan + " oldu.");

        // İksir tek kullanımlık
        oyuncu.envdenEsyaSil(this);
    }
}
