# Primer uporabe: Pregled vseh registriranih uporabnikov

**ID:** PU-08

**Cilj:** Administrator si ogleda seznam vseh uporabnikov, ki so registrirani v sistemu za spremljanje stroškov, in tako dobi pregled nad uporabniki sistema.

**Akterji:**
- Administrator

**Predpogoji:**
- Administrator je prijavljen v sistem.
- Administrator ima administratorske pravice.
- Sistem je dostopen in povezan s podatkovno bazo.

**Stanje sistema po PU:**
- Administrator vidi seznam vseh registriranih uporabnikov.
- Podatki v sistemu se ne spremenijo (gre za branje).

## Scenarij

1. Administrator izbere možnost "Pregled vseh registriranih uporabnikov".
2. Sistem preveri, ali ima prijavljeni uporabnik administratorske pravice.
3. Sistem iz podatkovne baze prebere vse registrirane uporabnike.
4. Sistem prikaže seznam uporabnikov (npr. ID, uporabniško ime, e-naslov, datum registracije).
5. Administrator pregleda seznam.

## Alternativni tokovi

**3a.** V sistemu ni nobenega registriranega uporabnika.
- Sistem prikaže obvestilo "Ni registriranih uporabnikov" in prazen seznam.

**4a.** Seznam uporabnikov je dolg.
- Sistem rezultate prikaže po straneh (paginacija).

**4b.** Administrator želi poiskati določenega uporabnika.
- Administrator vpiše iskalni niz (npr. ime ali e-naslov) in sistem prikaže samo ustrezne uporabnike.

## Izjeme

- **2a.** Uporabnik nima administratorskih pravic: sistem zavrne dostop in prikaže sporočilo »Nimate pravic za ogled seznama uporabnikov«.
- **2b.** Seja je potekla: sistem administratorja preusmeri na prijavno stran.
- **3b.** Povezava s podatkovno bazo ni uspela: sistem prikaže sporočilo o napaki (»Podatkov trenutno ni mogoče pridobiti, poskusite znova«) in napako zabeleži.
