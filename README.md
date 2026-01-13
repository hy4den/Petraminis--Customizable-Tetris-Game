# 🧩 Petraminis

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-Aktif-success?style=for-the-badge)
![License](https://img.shields.io/badge/Lisans-MIT-blue?style=for-the-badge)

**Petraminis**, klasik tetris benzeri blok yerleştirme mekaniklerini modern bir dokunuşla sunan, Java tabanlı eğlenceli bir bulmaca oyunudur. Stratejik düşünme ve hızlı reflekslerinizi test edin!

---

## 📑 İçindekiler
- [Hakkında](#-hakkında)
- [Özellikler](#-özellikler)
- [Geliştirici Ekip](#-geliştirici-ekip)
- [Kontroller](#-kontroller)
- [Kurulum ve Çalıştırma](#-kurulum-ve-çalıştırma)
- [Ekran Görüntüleri](#-ekran-görüntüleri)

---

## ℹ️ Hakkında

Bu proje, nesne yönelimli programlama (OOP) prensipleri kullanılarak **Java Swing** kütüphanesi ile geliştirilmiştir. Oyuncular, yukarıdan düşen farklı şekil ve renklerdeki "minoları" oyun alanına yerleştirerek tam satırlar oluşturmalı ve patlatmalıdır. Oyun ilerledikçe heyecan artar!

## ✨ Özellikler

- 🎨 **Renkli Grafikler:** Her biri farklı renge sahip çeşitli bloklar (Minolar).
- 🏆 **Puan & Skor Sistemi:** Satır temizleme ve kombolar ile yüksek puanlar kazanın.
- 💾 **Yüksek Skor Kaydı:** En iyi derecelerinizi veritabanına kaydedin.
- ⏸️ **Gelişmiş Menü:** Oyunu duraklatma, devam ettirme ve çıkış seçenekleri.
- 🎵 **Ses Efektleri:** Oyun atmosferini güçlendiren müzik ve sesler.
- 👤 **Oyuncu Profili:** Oyuna başlarken isminizi belirleyin.

---

## 👥 Geliştirici Ekip

Bu oyun, tutkulu geliştiriciler tarafından hazırlanmıştır:

| Geliştirici | Rol |
| :--- | :--- |
| **Ali Isakoca** | 👨‍💻 Lead Developer / Game Logic |
| **Alperen Dopaç** | 👨‍💻 Developer / UI & Design |

---

## 🕹️ Kontroller

Oyun klavye ile kolayca kontrol edilebilir:

| Tuş | İşlev | Açıklama |
| :---: | :--- | :--- |
| <kbd>W</kbd> | **Döndür** | Bloğu saat yönünde çevirir. |
| <kbd>A</kbd> | **Sola Git** | Bloğu sola hareket ettirir. |
| <kbd>D</kbd> | **Sağa Git** | Bloğu sağa hareket ettirir. |
| <kbd>S</kbd> | **Hızlı İndir** | Bloğu aşağıya hızla indirir. |
| <kbd>ESC</kbd> | **Menü / Duraklat** | Oyunu duraklatır ve menüyü açar. |
| <kbd>ENTER</kbd> | **Yeniden Başlat** | Oyunu baştan başlatır. |

---

## 🛠️ Kurulum ve Çalıştırma

### 📋 Gereksinimler
*   **Java JDK 11** veya üzeri yüklü olmalıdır.
*   Herhangi bir IDE (IntelliJ, Eclipse, VS Code) veya Terminal.

### 🚀 Hızlı Başlangıç (Terminal)

Projeyi terminal üzerinden hemen çalıştırmak için şu adımları izleyin:

1.  **Projeyi İndirin:**
    Proje dosyalarını bilgisayarınıza indirin ve klasör dizinine gidin.

2.  **Derleyin:**
    ```bash
    javac -d bin src/module-info.java src/main/*.java src/database/*.java src/mino/*.java
    ```

3.  **Çalıştırın:**
    ```bash
    java -cp bin main.Main
    ```

### � IDE ile Kurulum
1.  Proje klasörünü (`Petraminis`) IDE'nizde **"Open Project"** diyerek açın.
2.  `src` klasörünün "Sources Root" olarak işaretlendiğinden emin olun.
3.  `src/main/Main.java` dosyasına sağ tıklayın ve **"Run"** seçeneğini seçin.

<p align="center">
  <i>Keyifli Oyunlar Dileriz!</i><br>
  Developed with ❤️ in Java
</p>
