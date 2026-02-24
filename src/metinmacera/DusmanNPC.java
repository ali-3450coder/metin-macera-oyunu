package metinmacera;

public class DusmanNPC extends NPC {

    private int can;
    private int saldiriGucu;

    public DusmanNPC(String kod, String ad, int can, int saldiriGucu) {
        super(kod, ad);
        this.can = can;
        this.saldiriGucu = saldiriGucu;
    }

    public int getCan() {
        return can;
    }

    public int getSaldiriGucu() {
        return saldiriGucu;
    }

    public void saldir(Oyuncu oyuncu) {
        if (can <= 0) {
            System.out.println(getAd() + " zaten etkisiz hale getirilmiş görünüyor.");
            return;
        }

        int oyuncuEskiCan = oyuncu.getCan();
        int yeniCan = oyuncuEskiCan - saldiriGucu;
        if (yeniCan < 0) {
            yeniCan = 0;
        }
        oyuncu.setCan(yeniCan);

        System.out.println(getAd() + " sana saldırıyor!");
        System.out.println("Canın " + oyuncuEskiCan + " → " + yeniCan + " oldu.");
        // Oyun bitirme kontrolünü OyunMotoru yapıyor (can 0 ise END durumu).
    }
}
