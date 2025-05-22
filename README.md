# Hechaton - Kalite Kontrol Mobil Uygulaması (Android)

Bu Android uygulaması, kullanıcıdan alınan görselleri bir Flask API’ye göndererek ürün üzerindeki **renk**, **leke**, **kesim** ve **yapısal** bozuklukları tespit eder ve sonuçları grafiksel olarak raporlar.

## 🚀 Özellikler

- Galeriden veya varsayılan görsellerden seçim
- Flask API ile HTTP üzerinden analiz
- Ürünlerin hata oranlarını çubuk ve pasta grafiklerle gösterme
- Android Room ile veri saklama (isteğe bağlı)
- Dinamik uyarı mesajları

## 🛠️ Teknolojiler

- Android (Kotlin/Java)
- Retrofit (HTTP istemcisi)
- MPAndroidChart (Grafikler)
- Flask (Python taraflı API)
- OpenCV (Sunucu taraflı görsel işleme)

## 📁 Ana Yapı

- `MainActivity`: Kullanıcıdan görsel seçimi
- `ResultDisplayActivity`: Görsel analiz sonuçlarının listesi
- `ReportActivity`: Grafiksel hata raporları
- `network/ApiClient`: Retrofit istemcisi
- `models/Product`: Ürün modeli

## 📦 Kurulum

1. Android Studio ile projeyi açın
2. `local.properties` dosyasına Android SDK yolunu yazın
3. `gradlew build` ile derleyin
4. Flask API arka planda çalışmalı (örn. `http://192.168.x.x:5000/analyze`)

## 🔁 API Endpoint

```http
POST /analyze
Form-Data: image (binary)
Response: { "defect": true, "symmetry": false, "shift": 5.4 }
```

## 📊 Ekran Görüntüleri

- Bar chart: Hata türlerine göre dağılım
- Pie chart: Departman bazlı hata yüzdeleri
- Dinamik açıklamalar: En çok hata tipi için uyarı

## 🔒 Lisans

MIT Lisansı

