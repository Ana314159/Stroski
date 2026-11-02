package com.example.stroski.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kategorija")
public class Kategorija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    public Kategorija() {}

    public Kategorija(String naziv) {
        this.naziv = naziv;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
}