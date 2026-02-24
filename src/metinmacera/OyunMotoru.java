package metinmacera;

import java.util.Scanner;

public class OyunMotoru {

    private Scanner tarayici;
    private Oyuncu oyuncu;
    private boolean calisiyor;

    // Kırmızı Oda'dan Silah Odası'na giden kapının durumu
    private boolean kirmiziKapiAcik;

    public OyunMotoru() {
        this.tarayici = new Scanner(System.in);
        this.calisiyor = true;
        this.kirmiziKapiAcik = false;
    }

    public void baslat() {
    	System.out.println("\n╔════════════════════════════════════╗");
        System.out.println(  "║     Mini Macera Oyununa Hoşgeldin  ║");
        System.out.println(  "╚════════════════════════════════════╝");
        System.out.print("KOMUTLARI GÖRMEK İÇİN 'yardim' YAZABİLİRSİN\n");

        oyunuHazirla();
        oyunDongusu();
    }

    // 5 odalı harita + başlangıç eşyaları + NPC'ler
    private void oyunuHazirla() {

        Oda salon = new Oda(
                1,
                "salon",
                "eski bir avize ve tozlu koltuklar ile dolu sessiz bir salon"
        );

        Oda koridor = new Oda(
                2,
                "koridor",
                "uzun ve karanlıkbir koridor. Duvarlarda çatlaklar var."
        );

        Oda kirmiziOda = new Oda(
                3,
                "Kırmızı Oda",
                "karşında devasa bir kapı var."
        );

        Oda silahOdasi = new Oda(
                4,
                "Silah odası",
                "Duvarlarda asılı eski silah ve paslı zırhlar görüyorsun"
        );

        Oda sifaOdasi = new Oda(
                5,
                "Şifa Odası",
                "hafif yeşil ışık saçan huzurlu bir oda. İçerisi ferahlatıcı"
        );

        // ODA BAĞLANTILARI
        salon.cikisBagla("dogu", koridor);
        koridor.cikisBagla("bati", salon);

        koridor.cikisBagla("dogu", kirmiziOda);
        kirmiziOda.cikisBagla("bati", koridor);

        kirmiziOda.cikisBagla("dogu", silahOdasi);
        silahOdasi.cikisBagla("bati", kirmiziOda);

        salon.cikisBagla("guney", sifaOdasi);
        sifaOdasi.cikisBagla("kuzey", salon);

        // --- BAŞLANGIÇ EŞYALARI ---

        EsyaAnahtar anahtarKirmizi = new EsyaAnahtar(
                "anahtar_kirmizi",
                "Kırmızı Anahtar",
                "Kırmızı odaya açılan kapının kilidini açabileceği söyleniyor."
        );
        salon.esyaEkle(anahtarKirmizi);

        EsyaIksir iyilestirmeIksiri = new EsyaIksir(
                "iksir_kucuk",
                "Küçük İksir",
                "İçince biraz can yeniler.",
                20
        );
        sifaOdasi.esyaEkle(iyilestirmeIksiri);

        EsyaSilah eskiKilinc = new EsyaSilah(
                "kilic_pasli",
                "Paslı Kılıç",
                "Çok yeni değil ama hala iş görür.",
                5
        );
        silahOdasi.esyaEkle(eskiKilinc);

        // --- NPC'LER ---

        // Konuşma ağacı:
        KonusmaDugumu kok = new KonusmaDugumu(
                "Dikkatli ol yolcu. Kırmızı kapı kilitlidir. Anahtarı salonda bulabilirsin."
        );
        KonusmaDugumu dugumKim = new KonusmaDugumu(
                "Ben bu kalenin muhafızıyım. Görevim içeri girenleri uyarmak."
        );
        KonusmaDugumu dugumTesekkur = new KonusmaDugumu(
                "Rica ederim. Umarım sağ salim çıkarsın."
        );

        kok.secenekEkle(new KonusmaSecenegi("Teşekkür ederim.", dugumTesekkur));
        kok.secenekEkle(new KonusmaSecenegi("Sen kimsin?", dugumKim));
        dugumKim.secenekEkle(new KonusmaSecenegi("Anladım, teşekkürler.", dugumTesekkur));

        DostNPC muhafiz = new DostNPC("muhafiz", "Muhafız", kok);
        salon.npcEkle(muhafiz);

        DusmanNPC goblin = new DusmanNPC("goblin", "Silah Odasındaki Goblin", 30, 15);
        silahOdasi.npcEkle(goblin);

        this.oyuncu = new Oyuncu(salon);

        // Oyuna başlar başlamaz oyuncuya mevcut odayı göster
        oyuncu.getMevcutOda().odaBilgisiYaz();
    }

    private void oyunDongusu() {
        while (calisiyor) {
            try {
                System.out.print("> ");
                String giris = tarayici.nextLine().trim();

                if (giris.isEmpty()) {
                    continue;
                }

                komutIsle(giris);

                // OYUNCU ÖLDÜ MÜ? (can 0 veya altına düştüyse)
                if (calisiyor && oyuncu.getCan() <= 0) {
                    oyunuBitir("Canın tükendi. Oyun burada sona erdi.");
                }

            } catch (Exception e) {
                System.out.println("Beklenmeyen bir hata oluştu. Lütfen tekrar dene.");
            }
        }

        System.out.println("Program sonlandırılıyor...");
    }

    private void komutIsle(String giris) {
        // Komutu parçalıyoruz: ilk kelime komut, gerisi parametre
        String[] parcalar = giris.split("\\s+", 2);
        String komut = parcalar[0].toLowerCase();
        String arguman = parcalar.length > 1 ? parcalar[1] : "";

        switch (komut) {
            case "yardim":
                komutYardim();
                break;
            case "cik":
                komutCik();
                break;
            case "bak":
                komutBak();
                break;
            case "git":
                komutGit(arguman);
                break;
            case "al":
                komutAl(arguman);
                break;
            case "env":
                komutEnv();
                break;
            case "kullan":
                komutKullan(arguman);
                break;
            case "konus":
                komutKonus(arguman);
                break;
            case "yaz":
                komutYaz(arguman);
                break;
            default:
                System.out.println("Bilinmeyen komut.");
                break;
        }
    }

    private void komutYardim() {
        System.out.println("> YARDIM");
        System.out.println(" --- Kullanabileceğin Komutlar ---");
        System.out.println("yardim            -> bu ekranı gösterir");
        System.out.println("cik               -> oyundan çıkış yapar (özet gösterir)");
        System.out.println("bak               -> bulunduğun yeri inceler");
        System.out.println("git YON           -> belirtilen yöne git (kuzey, guney, dogu, bati)");
        System.out.println("al ESYA_KODU      -> bulunduğun odadaki eşyayı al");
        System.out.println("kullan ESYA_KODU  -> envanterindeki eşyayı kullan");
        System.out.println("konus NPC_KODU    -> bulunduğun odadaki karakterle konuş");
        System.out.println("yaz METIN         -> söylediğin metni ekrana yazdırır");
        System.out.println("env               -> envanterini göster");
        System.out.println();
        System.out.println("Örnekler:");
        System.out.println("  al anahtar_kirmizi");
        System.out.println("  kullan anahtar_kirmizi");
        System.out.println("  kullan iksir_kucuk");
        System.out.println("  kullan kilic_pasli");
        System.out.println("  konus muhafiz");
        System.out.println("  konus goblin");
    }

    private void komutCik() {
        oyunuBitir("Oyundan kendi isteğinle çıktın.");
    }

    private void komutBak() {
        oyuncu.getMevcutOda().odaBilgisiYaz();
    }

    private void komutGit(String yon) {
        if (yon.isEmpty()) {
            System.out.println("Hangi yöne gideceğini belirtmelisin.");
            return;
        }

        Oda mevcut = oyuncu.getMevcutOda();

        // Kırmızı Oda'dan doğuya gitmek isterse ve kapı kilitliyse engelle
        if (mevcut.getAd().equals("Kırmızı Oda")
                && yon.equalsIgnoreCase("dogu")
                && !kirmiziKapiAcik) {

            System.out.println("Kırmızı kapı kilitli.");
            return;
        }

        Oda hedef = mevcut.cikisGetir(yon);

        if (hedef == null) {
            System.out.println("Bu yönde çıkış yok.");
        } else {
            oyuncu.setMevcutOda(hedef);
            hedef.odaBilgisiYaz();
        }
    }

    private void komutAl(String esyaKodu) {
        if (esyaKodu.isEmpty()) {
            System.out.println("Hangi eşyayı alacağını belirtmelisin. Örn: al anahtar_kirmizi");
            return;
        }

        boolean basarili = oyuncu.esyaAl(esyaKodu);
        if (!basarili) {
            System.out.println("Böyle bir eşya/karakter bulunmuyor.");
        }
    }

    private void komutEnv() {
        oyuncu.envanterMetniYaz();
    }

    private void komutKullan(String esyaKodu) {
        if (esyaKodu.isEmpty()) {
            System.out.println("Hangi eşyayı kullanacağını belirtmelisin. Örn: kullan anahtar_kirmizi");
            return;
        }

        Esya esya = oyuncu.envanterdenEsyaBul(esyaKodu);
        if (esya == null) {
            System.out.println("Bu eşya sende yok.");
            return;
        }

        esya.kullan(oyuncu, this);
    }

    private void komutKonus(String npcKodu) {
        if (npcKodu.isEmpty()) {
            System.out.println("Hangi karakterle konuşacağını belirtmelisin. Örn: konus muhafiz");
            return;
        }

        Oda oda = oyuncu.getMevcutOda();
        NPC npc = oda.npcBul(npcKodu);

        if (npc == null) {
            System.out.println("Böyle bir eşya/karakter bulunmuyor.");
            return;
        }

        if (npc instanceof DostNPC dost) {
            dost.konus(oyuncu, tarayici);
        } else if (npc instanceof DusmanNPC dusman) {
            System.out.println(npc.getAd() + " pek konuşmaya niyetli değil gibi...");
            dusman.saldir(oyuncu);
        } else {
            System.out.println("Bu karakterle konuşulamıyor.");
        }
    }

    private void komutYaz(String metin) {
        if (metin.isEmpty()) {
            System.out.println("Ne söylemek istediğini yazmalısın. Örn: yaz merhaba");
            return;
        }

        System.out.println("Sen: " + metin);
    }

    // --- Kırmızı kapı kontrol metodları ---

    public boolean isKirmiziKapiAcik() {
        return kirmiziKapiAcik;
    }

    public void kirmiziKapiAc() {
        this.kirmiziKapiAcik = true;
    }

    // --- OYUN BİTİŞİ ---

    private void oyunuBitir(String sebep) {
        if (!calisiyor) {
            return;
        }
        calisiyor = false;

        System.out.println();
        System.out.println("=== OYUN BİTTİ ===");
        System.out.println(sebep);
        System.out.println("---------------------");
        System.out.println("Son konumun : " + oyuncu.getMevcutOda().getAd());
        System.out.println("Canın       : " + oyuncu.getCan());
        System.out.println("Saldırı gücün: " + oyuncu.getSaldiriGucu());
        System.out.println("Envanter durumun:");
        oyuncu.envanterMetniYaz();
        System.out.println("---------------------");
    }
}
