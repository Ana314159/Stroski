# Aplikacija za spremljanje stroškov

## 1. O projektu
Sistemska rešitev je namenjena enostavnemu vodenju, pregledu in analizi osebnih financ ter dnevnih izdatkov uporabnika.Uporabniku omogoča, da na enem mestu sproti beleži vse svoje dnevne, mesečne in letne izdatke ter jih razvršča po različnih kategorijah, kot so hrana, prevoz, zabava ali stanovanjski stroški. Z vnesenimi podatki aplikacija omogoča hiter vpogled v strukturo porabe, spremljanje finančnih odlivov skozi čas ter lažje načrtovanje mesečnega proračuna. Glavni namen rešitve je uporabniku ponuditi enostavno digitalno orodje za boljšo preglednost nad lastnim denarjem in nadzor nad dnevnimi izdatki.

## 2. Uporabljene tehnologije
* **Frontend:** HTML, CSS, JavaScript
* **Backend:** Java, Spring Boot (v2.x / v3.x), Spring Data JPA
* **Baza podatkov:** H2 / PostgreSQL 17

## 3. Struktura projekta
Projekt je razdeljen na dva glavna sklopa:
* `backend/` - Vsebuje strežniško logiko (kontrolerji, servisi, repozitoriji, entitete).
* `frontend/` - Vsebuje uporabniški vmesnik (`index.html`, skripte in stile).

## 4. Navodila za nameščanje in zagon
1. Namestite Javo (JDK 17 ali novejšo).
2. Odprite projekt v urejevalniku (npr. VS Code ali IntelliJ).
3. Poženite backend aplikacijo preko Spring Boot zagonskega razreda.
4. Odprite `index.html` v spletnem brskalniku.

## 5. Navodila za razvijalce
* Delo poteka prek Git repozitorija z uporabo ločenih vej (`branches`) za posamezne funkcionalnosti.
* Pred vsakim potiskanjem kode (`git push`) preverite, da aplikacija deluje lokalno.

## 6. Standardi kodiranja
* Uporaba smiselnih angleških poimenovanj za razrede, metode in spremenljivke.
* Dosledno upoštevanje MVC (Model-View-Controller) arhitekturnega vzorca.