package com.example.stroski.service;

import com.example.stroski.dto.RegistracijaZahteva;
import com.example.stroski.exception.PodvojenUporabnikException;
import com.example.stroski.model.Uporabnik;
import com.example.stroski.repository.UporabnikRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UporabnikService {

    private final UporabnikRepository uporabnikRepository;
    private final PasswordEncoder passwordEncoder;

    public UporabnikService(UporabnikRepository uporabnikRepository, PasswordEncoder passwordEncoder) {
        this.uporabnikRepository = uporabnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Uporabnik registriraj(RegistracijaZahteva zahteva) {
        String ime = zahteva.uporabniskoIme().trim();
        String email = zahteva.email().trim().toLowerCase();

        if (uporabnikRepository.existsByUporabniskoImeIgnoreCase(ime)) {
            throw new PodvojenUporabnikException("Uporabniško ime je že zasedeno.", "uporabniskoIme");
        }
        if (uporabnikRepository.existsByEmailIgnoreCase(email)) {
            throw new PodvojenUporabnikException("Uporabnik s tem e-naslovom že obstaja.", "email");
        }

        Uporabnik uporabnik = new Uporabnik(ime, email, passwordEncoder.encode(zahteva.geslo()));

        try {
            // saveAndFlush, da morebitno kršitev unikatne omejitve ujamemo tukaj (hkratni zahtevki)
            return uporabnikRepository.saveAndFlush(uporabnik);
        } catch (DataIntegrityViolationException e) {
            throw new PodvojenUporabnikException(
                    "Uporabnik s tem uporabniškim imenom ali e-naslovom že obstaja.", null);
        }
    }
}
