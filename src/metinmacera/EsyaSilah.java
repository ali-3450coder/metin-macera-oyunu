package metinmacera;

public class EsyaSilah extends Esya {

    private int saldiriArtisi;

    public EsyaSilah(String kod, String ad, String aciklama, int saldiriArtisi) {
        super(kod, ad, aciklama);
        this.saldiriArtisi = saldiriArtisi;
    }

    public int getSaldiriArtisi() {
        return saldiriArtisi;
    }

    @Override
    public void kullan(Oyuncu oyuncu, OyunMotoru baglam) {
        int eskiGuc = oyuncu.getSaldiriGucu();
        int yeniGuc = eskiGuc + saldiriArtisi;

        oyuncu.setSaldiriGucu(yeniGuc);

        System.out.println(getAd() + " kuşandın.");
        System.out.println("Saldırı gücün " + eskiGuc + " → " + yeniGuc + " oldu.");

        // Silah envanterde kalmaya devam ediyor
        // oyuncu.envdenEsyaSil(this);
    }
}
