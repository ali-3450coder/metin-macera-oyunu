# metinmacera

Konsol tabanlı metin macera oyunu. Oyuncu odalar arasında gezerek eşya toplar, karakterlerle etkileşime girer, belirli kapıları açar ve oyun içi kararlarla ilerler.

## Proje Özeti
- Oyun, bağlı odalardan oluşan küçük bir harita üzerinde çalışır.
- Oyuncu komutlarla hareket eder, eşyaları toplayıp kullanır.
- Dost ve düşman NPC etkileşimleri bulunur.
- Konuşma düğümleri/seçenekleri ile temel diyalog sistemi içerir.
- Oyuncu durumu (can, saldırı, envanter) oyun boyunca güncellenir.

## Oynanış Mekanikleri
- Yönlü hareket: `git kuzey`, `git guney`, `git dogu`, `git bati`
- Eşya yönetimi: odadan alma (`al`) ve envanterden kullanma (`kullan`)
- NPC etkileşimi: `konus npc_kodu`
- Oda inceleme: `bak`
- Yardım/çıkış: `yardim`, `cik`

## Teknik Yapı
- `Uygulama`: program giriş noktası.
- `OyunMotoru`: oyun döngüsü, komut ayrıştırma ve kural yönetimi.
- `Oda`: oda bilgisi, çıkışlar, oda içi içerik yönetimi.
- `Oyuncu`: konum, can/saldırı değerleri, envanter işlemleri.
- `NPC`, `DostNPC`, `DusmanNPC`: karakter hiyerarşisi.
- `Esya` ve alt sınıfları (`EsyaAnahtar`, `EsyaIksir`, `EsyaSilah`): item sistemi.
- `KonusmaDugumu`, `KonusmaSecenegi`: diyalog ağacı modeli.

## Gereksinimler
- Java JDK 17+

## Derleme
Proje klasöründe:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -encoding UTF-8 -d out src/metinmacera/*.java
```

## Çalıştırma
```powershell
java -cp out metinmacera.Uygulama
```

## Klasör Yapısı
- `src/metinmacera`: kaynak kodlar
- `out`: derleme çıktısı (git tarafından izlenmez)

## Notlar
- Komut tabanlı etkileşim yapısı, OOP ve durum yönetimi pratiği için uygundur.
- Eğitim amaçlı tasarlanmıştır; yeni odalar, eşyalar ve görevler kolayca eklenebilir.
