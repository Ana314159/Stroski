# Aplikacija za spremljanje stroškov
## 1. Opis rešitve:
Sistemska rešitev Stroski je namenjena enostavnemu vodenju, pregledu in analizi osebnih financ ter dnevnih izdatkov uporabnika. Glavni namen aplikacije je uporabniku ponuditi enostavno digitalno orodje za boljšo preglednost nad lastnim denarjem in nadzor nad dnevnimi izdatki, kar rešuje težavo nepreglednosti porabe in pomanjkanja nadzora nad mesečnim proračunom. Rešitev je namenjena posameznikom, ki želijo urejene osebne finance brez zapletenih programov.

Ključne funkcionalnosti:
Sprotno beleženje in shranjevanje dnevnih, mesečnih in letnih izdatkov.
Razvrščanje stroškov po namenskih kategorijah (hrana, prevoz, zabava, stanovanjski stroški).
Hiter vpogled v strukturo porabe in spremljanje finančnih odlivov skozi čas.
Načrtovanje in nadzor nad mesečnim proračunom.

Obseg rešitve in morebitne omejitve:
Obseg: Aplikacija pokriva osnovno in varno vodenje osebnih izdatkov posameznega registriranega uporabnika, pregled po kategorijah ter podatkovno shranjevanje v relacijski bazi.
Omejitve: Rešitev trenutno ne omogoča neposredne avtomatske povezave z bančnimi računi (uvoz transakcij iz banke poteka ročno), prav tako ni namenjena večuporabniškim poslovnim računovodskim evidencam ali deljenju skupnih gospodinjskih proračunov med več osebami.

Domenski besednjak
Tukaj je seznam ključnih domenskih izrazov, specifičnih za področje osebnega proračuna in financ, ki se uporabljajo v aplikaciji:

Strošek (Expense): Vsak finančni odliv ali izdatek, ki ga uporabnik nameni za nakup blaga, storitev ali poravnavo obveznosti.
Kategorija (Category): Logična skupina ali sklop, v katerega se uvrščajo sorodni izdatki (npr. hrana, prevoz, zabava), kar omogoča lažjo analizo porabe.
Mesečni proračun (Monthly Budget): Finančni okvir ali vnaprej določena meja razpoložljivih sredstev, ki jih je uporabnik pripravljen ali zmožen zapraviti v koledarskem mesecu.
Finančni odliv (Financial Outflow): Vsako zmanjšanje razpoložljivih denarnih sredstev uporabnika zaradi nastalih izdatkov.
Zabeležka (Entry): Posamičen zapis o nastalem strošku, ki vključuje znesek, datum, opis in pripadajočo kategorijo.

## 2. Uporabljene tehnologije
* **Frontend:** HTML5, CSS3, JavaScript
* **Backend:** Java(JDK 17), Spring Boot (v2.x / v3.x), Spring Data JPA, Spring Web
* **Baza podatkov:** PostgreSQL 17
* **Orodja in okolja:**Git, GitHub, VS Code (in/ali IntelliJ IDEA)

## 3. Struktura projekta
Projekt je v korenskem imeniku razdeljen na dva glavna sklopa:
- `backend/`: Vsebuje celotno strežniško logiko Spring Boot aplikacije:
  - `src/main/java/com/.../StroskiApplication.java`: Glavni zagonski razred z anotacijo `@SpringBootApplication`.
  - `src/main/java/com/.../entity/Strosek.java`: Podatkovna entiteta (Java razred z `@Entity`), ki preslikava tabelo stroškov v bazi.
  - `src/main/java/com/.../repository/StrosekRepository.java`: Vmesnik (deduje `JpaRepository`), ki omogoča avtomatske poizvedbe in shranjevanje v PostgreSQL.
  - `src/main/resources/application.properties`: Nastavitve za povezavo s PostgreSQL bazo podatkov in JPA nastavitve.
- `frontend/`: Vsebuje uporabniški vmesnik:
  - `index.html`: Glavna spletna stran z vnosnimi polji in prikazom stroškov.
  - Skripte in stilne datoteke za povezavo s strežnikom ter vizualni prikaz.

## 4. Navodila za nameščanje in zagon
**Namestitev predpogojev:**
1. - Namestite Javo (**JDK 17**).
   - Namestite in zaženite **PostgreSQL 17**.
   - Namestite urejevalnik kode (npr. **VS Code** ali **IntelliJ IDEA**).
2. **Kloniranje repozitorija:**
   - Prenesite projekt z ukazem `git clone https://github.com/Ana314159/Stroski.git`.
3. **Konfiguracija baze:**
   - V PostgreSQL ustvarite bazo podatkov ter v datoteki `backend/src/main/resources/application.properties` nastavite ustrezne podatke za povezavo (`spring.datasource.url`, uporabniško ime in geslo).
4. **Zagon backend aplikacije:**
   - Odprite mapo `backend`, poiščite Spring Boot zagonski razred (`StroskiApplication.java`) in poženite aplikacijo.
5. **Zagon frontenda:**
   - Odprite mapo `frontend` in datoteko `index.html` zaženite v spletnem brskalniku (priporočeno preko vtičnika *Live Server* v VS Code).

## 5. Navodila za razvijalce
**Delovni tok z Git repozitorijem:**
  - Delo poteka prek Git repozitorija in povezave z GitHubom (`https://github.com/Ana314159/Stroski`).
  - Uporabljajo se ločene veje (`branches`) za posamezne funkcionalnosti ali popravke.
  - Pred vsakim potiskanjem kode (`git push origin main`) obvezno lokalno preverite, da aplikacija brezhibno deluje in se uspešno povezuje s PostgreSQL bazo.
- **Upravljanje s podatki:**
  - Spremembe struktur podatkov se izvajajo prek Java entitet z anotacijo `@Entity`. Spring Data JPA (`JpaRepository`) poskrbi za samodejno upravljanje tabel v bazi brez potrebe po ročnem pisanju surovega SQL-a.
## 6. Standardi kodiranja
### Pravila poimenovanja v Javi (Naming Conventions)
- **Razredi in entitete:** Uporaba *PascalCase* zapisa, kjer se vsaka beseda začne z veliko črko (npr. `Strosek`, `StrosekRepository`, `StroskiApplication`).
- **Metode in spremenljivke:** Uporaba *camelCase* zapisa, kjer se ime začne z malo črko, vsaka naslednja beseda pa z veliko (npr. `znesekStroska`, `findStroskiByNaziv`, `idStroska`).
- **Jezik kode:** Imena razredov, spremenljivk, metod ter stolpcev v bazi podatkov naj bodo napisana v angleškem jeziku (ali dosledno v slovenskem, pri čemer je angleščina v programiranju standard).

### Struktura in arhitektura kode (MVC / Slojna arhitektura)
- **Ločevanje skrbi:** Koda mora biti strogo ločena na sloje (Controllers za HTTP vmesnik, Services za poslovno logiko in Repositories za dostop do podatkov preko JPA).
- **Brez surovega SQL-a:** V zalednem delu se ne pišejo surove SQL poizvedbe, temveč se za podatkovne operacije uporabljajo vgrajene metode Spring Data JPA vmesnikov (npr. `save()`, `findAll()`, `deleteById()`).

### Frontend standardi
- **Preglednost HTML-ja:** HTML datoteke (`index.html`) morajo imeti čisto in pregledno strukturo elementov.
- **Asinhroni klici:** JavaScript koda za klicanje backend REST storitev mora biti ustrezno komentirana in organizirana z uporabo modernih asinhronih klicev (`fetch` API).

## 7. Diagram primerov uporabe 
<img width="1199" height="637" alt="image" src="https://github.com/user-attachments/assets/86995cec-b4ad-4241-b9a0-7a5444c44a93" />
