package metinmacera;

import java.util.ArrayList;
import java.util.List;

public class Oyuncu {

    private Oda mevcutOda;
    private List<Esya> envanter;
    private int can;
    private int saldiriGucu;

    public Oyuncu(Oda baslangicOdasI) {
        this.mevcutOda = baslangicOdasI;
        this.envanter = new ArrayList<>();
        this.can = 100;
        this.saldiriGucu = 10;
    }

    public Oda getMevcutOda() {
        return mevcutOda;
    }

    public void setMevcutOda(Oda mevcutOda) {
        this.mevcutOda = mevcutOda;
    }

    public List<Esya> getEnvanter() {
        return envanter;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

    public int getSaldiriGucu() {
        return saldiriGucu;
    }

    public void setSaldiriGucu(int saldiriGucu) {
        this.saldiriGucu = saldiriGucu;
    }

    // --- ENVANTER İŞLEMLERİ ---

    public boolean esyaAl(String esyaKodu) {
        if (mevcutOda == null) {
            return false;
        }

        Esya esya = mevcutOda.esyaBul(esyaKodu);
        if (esya == null) {
            return false;
        }

        mevcutOda.esyaCikar(esya);
        envanter.add(esya);
        System.out.println(esya.getAd() + " envanterine eklendi.");
        return true;
    }

    public Esya envanterdenEsyaBul(String kod) {
        String aranan = kod.toLowerCase();
        for (Esya e : envanter) {
            if (e.getKod().toLowerCase().equals(aranan)) {
                return e;
            }
        }
        return null;
    }

    public void envdenEsyaSil(Esya esya) {
        envanter.remove(esya);
    }

    public void envanterMetniYaz() {
        if (envanter.isEmpty()) {
            System.out.println("Envanterin boş.");
            System.out.println("Can: " + can + " | Saldırı gücü: " + saldiriGucu);
            return;
        }

        System.out.println("Envanterin:");
        for (Esya e : envanter) {
            System.out.println(" - " + e.getKod() + " (" + e.getAd() + ")");
        }
        System.out.println("Can: " + can + " | Saldırı gücü: " + saldiriGucu);
    }
}
