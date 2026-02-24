package metinmacera;

public class EsyaAnahtar extends Esya {

    public EsyaAnahtar(String kod, String ad, String aciklama) {
        super(kod, ad, aciklama);
    }

    @Override
    public void kullan(Oyuncu oyuncu, OyunMotoru baglam) {
        Oda oda = oyuncu.getMevcutOda();

        if (!oda.getAd().equals("Kırmızı Oda")) {
            System.out.println(getAd() + " burada bir işe yaramıyor.");
            return;
        }

        if (baglam.isKirmiziKapiAcik()) {
            System.out.println("Kırmızı kapı zaten açık.");
            return;
        }

        baglam.kirmiziKapiAc();
        System.out.println("Kırmızı kapının kilidini açtın. Artık doğuya geçebilirsin.");
        //tek kullanımlık yapılabilir
        // oyuncu.envdenEsyaSil(this);
    }
}
