# bitirmeprojesi-SalihSulak
bitirmeprojesi-SalihSulak created by GitHub Classroom

Projenin Konusu: Bir marketteki ürünlerin satış fiyatlarına göre son fiyatlarını belirleyen servisin Spring Boot Framework kullanılarak yazılması ve isteğe bağlı olarak önyüzünün yazılması

Proje'nin arka ucunu Java Spring Framework ile yazdım. Veritabanı olarak Postgresql kullandım. Ön uçta ise React.js kullandım.Backendde Open API Specification için Swagger kullandım.

localhost:8080/api/v1/auth/register endpointine post request ile kullanıcı sisteme kayıt olur.

localhost:8080/api/v1/auth/login endpointine post request ile kullanıcı sisteme giriş yapar.

localhost:8080/api/v1/users endpointine put request yapılarak kullanıcı verileri güncellenebilir.

localhost:8080/api/v1/users/:id endpointine delete request ile kullanıcı silinebilir.

localhost:8080/api/v1/products endpointine post request yapılarak ürün kaydedilir.

localhost:8080/api/v1/products endpointine get request ile tüm ürün verileri getirilir.

localhost:8080/api/v1/products?start_price=?end_price endpointine get request ile fiyat aralığına göre ürün verileri getirilir.

localhost:8080/api/v1/products/:productType endpointine get request ürün tipine göre ürün verileri getirilir.

localhost:8080/api/v1/products/:productType/analysis endpointine get request ürün tipine göre ürün istatikleri getirilir.

localhost:8080/api/v1/products endpointine put request ile ürün verileri güncellenir.

localhost:8080/api/v1/products/:id endpointine delete request ile ürün silinir.

localhost:8080/api/v1/vat-rates endpointine put request ile KDV oranı verileri güncellenir.


> **Gereksinimler:**

> **Backend:**

- Kullanıcıdan kullanıcı adı, şifre, isim, soy isim bilgilerini alarak sisteme kayıt yapılır.
- Sisteme kayıtlı kullanıcılar market ürünlerinin veri girişini yapabilir.
- Ürün türlerine göre KDV oranları değişiklik göstermektedir. Bu oranlar aşağıdaki tabloda
belirtilmiştir. __**Zaman zaman KDV oranları değişiklik gösterebilmektedir.**__

![Image](https://www.linkpicture.com/q/Untitled_395.png)


- Ürün için veri girişi yapacak kullanıcı; ürünün adı, ürünün türü ve vergisiz satış fiyatı alanlarını
doldurur. Her bir ürün için KDV Tutarı ve ürünün son fiyatı da hesaplanarak sisteme kaydedilir.
> **Kurallar ve gereksinimler:**
- Sisteme yeni kullanıcı tanımlanabilir, güncellenebilir ve silinebilir.
- Sisteme yeni ürünler tanımlanabilir, güncellenebilir ve silinebilir.
- Ürünlerin fiyatları güncellenebilir.
- KDV oranları değiştiğinde sistemdeki ürünlere de bu değişiklik yansıtılmalıdır. Herhangi bir hata
durumunda tüm işlemler geri alınmalıdır.
- Tüm ürünler listelenebilmelidir.
- Ürün türlerine göre ürünler listelenebilmelidir.
- Belirli bir fiyat aralığındaki ürünler listelenebilmelidir.
- Ürün türlerine göre aşağıdaki gibi detay veri içeren bir bilgilendirme alınabilmelidir.

![Image](https://www.linkpicture.com/q/22_57.png)

> Validasyonlar:
- Aynı kullanıcı adı ile kullanıcı tanımı yapılamaz.
- Kullanıcı girişi kullanıcı adı & şifre kombinasyonu ile yapılır.
- Ürün türü, fiyatı, adı gibi alanlar boş olamaz.
- Ürün fiyatı sıfır ya da negatif olamaz.
- KDV oranı negatif olamaz.
> Authentication:
- Güvenli endpointler kullanınız. (jwt, bearer vs. )
> Response:
- Başarılı ve başarısız responselar için modeller tanımlayın ve bunları kullanın.
> Dökümantasyon:
- Open API Specification (Swagger tercih sebebi)
> Exception Handling:
- Hatalı işlemler için doğru hata kodlarının dönüldüğünden emin olunuz.
> Test:
- Unit ve integration testleri yazınız. 
