# Uporabniške zgodbe

Funkcionalnost: **Registracija uporabnika**

> Odgovorni član je naveden kot `[ime člana]`. Pred oddajo ga zamenjajte z dejanskim imenom.

---

## US-01: Registracija novega uporabnika

**Odgovorni član:** [ime člana]
**GitHub Issue:** #[številka]

**Uporabniška zgodba:**
Kot **obiskovalec brez računa** želim **ustvariti račun z uporabniškim imenom, e-naslovom in geslom**, da **lahko začnem voditi svoje osebne stroške.**

### Sprejemni kriteriji

**Kriterij 1: uspešna registracija**
- **Given:** Obiskovalec je na strani za registracijo in uporabniškega imena ter e-naslova še nihče ni uporabil.
- **When:** Vnese veljavno uporabniško ime, veljaven e-naslov, geslo in ponovitev gesla ter pritisne »Registriraj se«.
- **Then:** Sistem ustvari račun, prikaže sporočilo o uspešni registraciji z uporabniškim imenom in izprazni obrazec.

**Kriterij 2: gesli se ne ujemata**
- **Given:** Obiskovalec je na strani za registracijo.
- **When:** V polji »Geslo« in »Ponovi geslo« vnese različni vrednosti ter pritisne »Registriraj se«.
- **Then:** Sistem prikaže napako »Gesli se ne ujemata.«, račun se ne ustvari, vneseni podatki pa ostanejo v obrazcu.

**Kriterij 3: obvezna polja**
- **Given:** Obiskovalec je na strani za registracijo.
- **When:** Pusti prazno vsaj eno obvezno polje (uporabniško ime, e-naslov ali geslo) in pritisne »Registriraj se«.
- **Then:** Sistem ob praznem polju prikaže napako, da je polje obvezno, in račun se ne ustvari.

### Podrobnosti implementacije
- Backend: končna točka `POST /api/auth/registracija` (`AuthController`), poslovna logika v `UporabnikService.registriraj()`.
- Podatki se shranijo v tabelo `uporabnik` prek entitete `Uporabnik` in `UporabnikRepository` (Spring Data JPA, brez surovega SQL-a).
- Geslo se nikoli ne shrani v čistopisu, ampak kot BCrypt hash (`spring-security-crypto`). Odgovor strežnika ne vsebuje gesla ali hasha (`UporabnikOdgovor`).
- E-naslov se pred shranjevanjem pretvori v male črke in odstranijo se presledki.
- Ob uspehu strežnik vrne status 201 (Created).
- Frontend: stran `frontend/public/registracija.html`, klic prek `fetch` API, besedilo napak se vstavlja s `textContent`.
- Ujemanje gesel se preveri na odjemalcu, vse ostalo pa vedno še enkrat na strežniku.
- Omejitev: prijave še ni, zato uporabnik po registraciji ni samodejno prijavljen.

### Naloge (sub-issues)
| ID | Naziv | Opis |
|----|-------|------|
| T-01.1 | Backend: entiteta, repozitorij in storitev za registracijo | Ustvariti `Uporabnik`, `UporabnikRepository`, `UporabnikService` in bean `PasswordEncoder`. Geslo se shrani kot BCrypt hash. V `pom.xml` dodati `spring-boot-starter-validation` in `spring-security-crypto`. |
| T-01.2 | Backend: končna točka za registracijo | Dodati `AuthController` s `POST /api/auth/registracija`, DTO `RegistracijaZahteva` in `UporabnikOdgovor` ter vračanje statusa 201. |
| T-01.3 | Frontend: obrazec za registracijo | Ustvariti `registracija.html` s polji, preverbo ujemanja gesel, klicem `fetch`, prikazom uspeha in povezavo iz `index.html`. |

---

## US-02: Jasna sporočila ob neveljavnem vnosu

**Odgovorni član:** [ime člana]
**GitHub Issue:** #[številka]

**Uporabniška zgodba:**
Kot **obiskovalec, ki se registrira** želim **ob napačnem vnosu dobiti razumljivo sporočilo ob ustreznem polju**, da **lahko podatke hitro popravim brez ugibanja.**

### Sprejemni kriteriji

**Kriterij 1: prekratko ali preenostavno geslo**
- **Given:** Obiskovalec je na strani za registracijo in je vse ostalo pravilno izpolnil.
- **When:** Vnese geslo, ki je krajše od 8 znakov ali nima vsaj ene črke in ene številke, ter pritisne »Registriraj se«.
- **Then:** Sistem pod poljem »Geslo« prikaže pojasnilo, kaj geslo mora izpolnjevati, in račun se ne ustvari.

**Kriterij 2: neveljaven e-naslov**
- **Given:** Obiskovalec je na strani za registracijo.
- **When:** V polje »E-naslov« vnese besedilo, ki ni e-naslov (na primer `ana@`), ter pritisne »Registriraj se«.
- **Then:** Sistem pod poljem »E-naslov« prikaže napako »E-naslov ni veljaven.« in račun se ne ustvari.

**Kriterij 3: neveljavno uporabniško ime**
- **Given:** Obiskovalec je na strani za registracijo.
- **When:** Vnese uporabniško ime, ki ima manj kot 3 znake, več kot 30 znakov ali vsebuje nedovoljene znake (presledek, šumnike), ter pritisne »Registriraj se«.
- **Then:** Sistem pod poljem »Uporabniško ime« prikaže pojasnilo o dovoljeni obliki in račun se ne ustvari.

### Podrobnosti implementacije
- Pravila so zapisana z anotacijami Jakarta Validation na `RegistracijaZahteva`:
  - uporabniško ime: 3–30 znakov, dovoljeno `A-Z a-z 0-9 _ . -`;
  - e-naslov: do 100 znakov in oblika `nekaj@nekaj.nekaj`;
  - geslo: 8–72 znakov (BCrypt obdela največ 72 bajtov), vsaj ena črka in ena številka.
- `GlobalniHandlerNapak` (`@RestControllerAdvice`) ujame `MethodArgumentNotValidException` in vrne status 400 z JSON-om `{"sporocilo": "...", "napake": {"polje": "sporočilo"}}`.
- Frontend iz `napake` vsako sporočilo izpiše pod polje z istim imenom.
- Če strežnik ni dosegljiv, frontend prikaže splošno obvestilo o napaki.

### Naloge (sub-issues)
| ID | Naziv | Opis |
|----|-------|------|
| T-02.1 | Backend: validacijska pravila in obdelava napak | Dodati validacijske anotacije v `RegistracijaZahteva` in `GlobalniHandlerNapak`, ki vrača status 400 s sporočili po poljih. |
| T-02.2 | Frontend: prikaz napak ob poljih | V `registracija.html` prikazati sporočila iz odgovora `napake` pod ustreznimi polji in počistiti stara sporočila ob vsaki novi oddaji. |
| T-02.3 | Testiranje neveljavnih vnosov | Ročno ali z avtomatskim testom preveriti primere: kratko geslo, geslo brez številke, e-naslov brez domene, uporabniško ime s presledkom. |

---

## US-03: Preprečitev podvojenega računa

**Odgovorni član:** [ime člana]
**GitHub Issue:** #[številka]

**Uporabniška zgodba:**
Kot **obiskovalec** želim **da sistem ne dovoli dveh računov z istim uporabniškim imenom ali e-naslovom**, da **je moj račun enoličen in ga nihče ne more zamenjati z drugim.**

### Sprejemni kriteriji

**Kriterij 1: zasedeno uporabniško ime**
- **Given:** V sistemu že obstaja uporabnik z uporabniškim imenom `ana`.
- **When:** Nov obiskovalec poskusi registracijo z uporabniškim imenom `Ana` ali `ana` in pritisne »Registriraj se«.
- **Then:** Sistem pod poljem »Uporabniško ime« prikaže napako »Uporabniško ime je že zasedeno.« in drugega računa ne ustvari.

**Kriterij 2: že registriran e-naslov**
- **Given:** V sistemu že obstaja uporabnik z e-naslovom `ana@example.com`.
- **When:** Nov obiskovalec poskusi registracijo z e-naslovom `ANA@example.com` in pritisne »Registriraj se«.
- **Then:** Sistem pod poljem »E-naslov« prikaže napako »Uporabnik s tem e-naslovom že obstaja.« in drugega računa ne ustvari.

**Kriterij 3: uspešna registracija z drugačnimi podatki**
- **Given:** V sistemu že obstaja uporabnik z uporabniškim imenom `ana` in e-naslovom `ana@example.com`.
- **When:** Obiskovalec se registrira z drugim uporabniškim imenom in drugim e-naslovom.
- **Then:** Sistem ustvari nov račun, obstoječi uporabnik pa ostane nespremenjen.

### Podrobnosti implementacije
- `UporabnikRepository.existsByUporabniskoImeIgnoreCase()` in `existsByEmailIgnoreCase()` preverita podvojenost brez razlikovanja velikih in malih črk.
- Če uporabnik obstaja, `UporabnikService` vrže `PodvojenUporabnikException`, `GlobalniHandlerNapak` pa jo pretvori v status 409 (Conflict) z imenom polja.
- Na bazi sta še unikatni omejitvi `uq_uporabnik_ime` in `uq_uporabnik_email`, ki zaščitita tudi pred hkratnima zahtevkoma. Takšno kršitev ujame `saveAndFlush` in jo prav tako pretvori v 409.
- Omejitev: za uporabniško ime baza loči velike in male črke, zato je to pravilo zagotovljeno le na ravni storitve. Za strožjo zaščito bi bil potreben unikatni indeks na `lower(uporabnisko_ime)`.

### Naloge (sub-issues)
| ID | Naziv | Opis |
|----|-------|------|
| T-03.1 | Backend: preverjanje podvojenosti | Dodati `exists...IgnoreCase` metodi, `PodvojenUporabnikException` in obdelavo v `GlobalniHandlerNapak` (status 409). |
| T-03.2 | Baza: unikatni omejitvi | Na entiteti `Uporabnik` definirati `@UniqueConstraint` za uporabniško ime in e-naslov ter preveriti, da ju Hibernate ustvari. |
| T-03.3 | Testiranje podvojene registracije | Dvakrat zaporedoma registrirati istega uporabnika (tudi z drugačnimi velikimi črkami) in preveriti sporočila ter da je v bazi le en zapis. |

---

## Definition of Ready: pregled pred implementacijo

| Zgodba | Jasno definirana | Sprejemni kriteriji jasni in merljivi | Ekipa razume namen | Podrobnosti implementacije zapisane |
|--------|:---:|:---:|:---:|:---:|
| US-01 | ✔ | ✔ | ✔ | ✔ |
| US-02 | ✔ | ✔ | ✔ | ✔ |
| US-03 | ✔ | ✔ | ✔ | ✔ |

## Preverjanje po implementaciji

Pred zaprtjem posamezne zgodbe preverite, da funkcionalnost deluje v skladu z zapisanimi podatki, še posebej s sprejemnimi kriteriji.

| Zgodba | Kriterij 1 | Kriterij 2 | Kriterij 3 |
|--------|:---:|:---:|:---:|
| US-01 | ☐ | ☐ | ☐ |
| US-02 | ☐ | ☐ | ☐ |
| US-03 | ☐ | ☐ | ☐ |
