package metinmacera;

import java.util.List;
import java.util.Scanner;

public class DostNPC extends NPC {

    private KonusmaDugumu kok;

    public DostNPC(String kod, String ad, KonusmaDugumu kok) {
        super(kod, ad);
        this.kok = kok;
    }

    public void konus(Oyuncu oyuncu, Scanner tarayici) {
        KonusmaDugumu simdiki = kok;

        while (true) {
            System.out.println();
            System.out.println(getAd() + ": " + simdiki.getMetin());

            List<KonusmaSecenegi> secenekler = simdiki.getSecenekler();
            if (secenekler.isEmpty()) {
                System.out.println("(Konuşma burada sona erdi.)");
                break;
            }

            System.out.println("Seçeneklerin:");
            for (int i = 0; i < secenekler.size(); i++) {
                System.out.println("  " + (i + 1) + ") " + secenekler.get(i).getMetin());
            }
            System.out.println("  0) Konuşmayı bitir");

            System.out.print("Seçimin: ");
            String giris = tarayici.nextLine().trim();

            int secim;
            try {
                secim = Integer.parseInt(giris);
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz seçim.");
                continue;
            }

            if (secim == 0) {
                System.out.println(getAd() + ": Görüşürüz.");
                break;
            }

            if (secim < 1 || secim > secenekler.size()) {
                System.out.println("Geçersiz seçim.");
                continue;
            }

            KonusmaSecenegi secilen = secenekler.get(secim - 1);
            if (secilen.getSonraki() == null) {
                System.out.println(getAd() + ": Konuşma burada bitiyor.");
                break;
            }

            simdiki = secilen.getSonraki();
        }
    }
}
