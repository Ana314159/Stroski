package com.example.stroski.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "uporabnik", // "user" je v PostgreSQL rezervirana beseda
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_uporabnik_ime", columnNames = "uporabnisko_ime"),
        @UniqueConstraint(name = "uq_uporabnik_email", columnNames = "email")
    }
)
public class Uporabnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uporabnisko_ime", nullable = false, length = 30)
    private String uporabniskoIme;

    @Column(nullable = false, length = 100)
    private String email;

    // Hash gesla (BCrypt), nikoli gesla v čistopisu
    @Column(name = "geslo_hash", nullable = false)
    private String gesloHash;

    @Column(name = "datum_registracije", nullable = false)
    private LocalDateTime datumRegistracije;

    @Column(nullable = false, length = 20)
    private String vloga = "UPORABNIK";

    public Uporabnik() {}

    public Uporabnik(String uporabniskoIme, String email, String gesloHash) {
        this.uporabniskoIme = uporabniskoIme;
        this.email = email;
        this.gesloHash = gesloHash;
        this.datumRegistracije = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUporabniskoIme() { return uporabniskoIme; }
    public void setUporabniskoIme(String uporabniskoIme) { this.uporabniskoIme = uporabniskoIme; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGesloHash() { return gesloHash; }
    public void setGesloHash(String gesloHash) { this.gesloHash = gesloHash; }

    public LocalDateTime getDatumRegistracije() { return datumRegistracije; }
    public void setDatumRegistracije(LocalDateTime datumRegistracije) { this.datumRegistracije = datumRegistracije; }

    public String getVloga() { return vloga; }
    public void setVloga(String vloga) { this.vloga = vloga; }
}
