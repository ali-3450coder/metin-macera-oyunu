package metinmacera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Oda {

    private int id;
    private String ad;
    private String aciklama;
    private Map<String, Oda> cikislar;
    private List<Esya> esyalar;
    private List<NPC> npcler;

    public Oda(int id, String ad, String aciklama) {
        this.id = id;
        this.ad = ad;
        this.aciklama = aciklama;
        this.cikislar = new HashMap<>();
        this.esyalar = new ArrayList<>();
        this.npcler = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getAciklama() {
        return aciklama;
    }

    // --- ÇIKIŞLAR ---

    public void cikisBagla(String yon, Oda hedefOda) {
        // yon: "kuzey", "guney", "dogu", "bati"
        cikislar.put(yon.toLowerCase(), hedefOda);
    }

    public Oda cikisGetir(String yon) {
        return cikislar.get(yon.toLowerCase());
    }

    public Map<String, Oda> getCikislar() {
        return cikislar;
    }

    // --- EŞYALAR ---

    public void esyaEkle(Esya esya) {
        esyalar.add(esya);
    }

    public void esyaCikar(Esya esya) {
        esyalar.remove(esya);
    }

    public List<Esya> getEsyalar() {
        return esyalar;
    }

    public Esya esyaBul(String kod) {
        String aranan = kod.toLowerCase();
        for (Esya e : esyalar) {
            if (e.getKod().toLowerCase().equals(aranan)) {
                return e;
            }
        }
        return null;
    }

    // --- NPC'LER ---

    public void npcEkle(NPC npc) {
        npcler.add(npc);
    }

    public void npcCikar(NPC npc) {
        npcler.remove(npc);
    }

    public List<NPC> getNpcler() {
        return npcler;
    }

    public NPC npcBul(String kod) {
        String aranan = kod.toLowerCase();
        for (NPC n : npcler) {
            if (n.getKod().toLowerCase().equals(aranan)) {
                return n;
            }
        }
        return null;
    }

    public void odaBilgisiYaz() {
        System.out.println("Şu an bulunduğun yer: " + ad);
        System.out.println(aciklama);

        if (cikislar.isEmpty()) {
            System.out.println("Buradan çıkış görünmüyor.");
        } else {
            System.out.print("Çıkışlar: ");
            System.out.println(String.join(", ", cikislar.keySet()));
        }

        if (esyalar.isEmpty()) {
            System.out.println("Eşyalar: (yok)");
        } else {
            System.out.print("Eşyalar: ");
            boolean ilk = true;
            for (Esya e : esyalar) {
                if (!ilk) {
                    System.out.print(", ");
                }
                System.out.print(e.getKod());
                ilk = false;
            }
            System.out.println();
        }

        if (npcler.isEmpty()) {
            System.out.println("Karakterler: (yok)");
        } else {
            System.out.print("Karakterler: ");
            boolean ilk = true;
            for (NPC n : npcler) {
                if (!ilk) {
                    System.out.print(", ");
                }
                System.out.print(n.getKod());
                ilk = false;
            }
            System.out.println();
        }
    }
}
