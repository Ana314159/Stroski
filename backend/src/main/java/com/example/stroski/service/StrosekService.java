package com.example.stroski.service;

import com.example.stroski.model.Strosek;
import com.example.stroski.repository.StrosekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StrosekService {

    @Autowired
    private StrosekRepository strosekRepository;

    public List<Strosek> vrniVseStroske(String naziv, Long kategorijaId) {
    if (naziv != null && !naziv.trim().isEmpty()) {
        return strosekRepository.findByNazivContainingIgnoreCase(naziv);//baza 
    }
    if (kategorijaId != null) {
        return strosekRepository.findByKategorijaId(kategorijaId);
    }
    return strosekRepository.findAll();
}


    public Optional<Strosek> vrniStrosekPoId(Long id) {
        return strosekRepository.findById(id);
    }

    public Strosek dodajStrosek(Strosek strosek) {
        return strosekRepository.save(strosek);
    }

    public Strosek posodobiStrosek(Long id, Strosek noviStrosek) {
        return strosekRepository.findById(id).map(strosek -> {
            strosek.setNaziv(noviStrosek.getNaziv());
            strosek.setZnesek(noviStrosek.getZnesek());
            strosek.setKategorija(noviStrosek.getKategorija());
            strosek.setDatum(noviStrosek.getDatum());
            return strosekRepository.save(strosek);
        }).orElseThrow(() -> new RuntimeException("Strošek z ID " + id + " ne obstaja."));
    }

    public void izbrisiStrosek(Long id) {
        strosekRepository.deleteById(id);
    }
}