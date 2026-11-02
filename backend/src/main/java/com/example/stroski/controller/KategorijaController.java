package com.example.stroski.controller;

import com.example.stroski.model.Kategorija;
import com.example.stroski.repository.KategorijaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kategorije")
@CrossOrigin(origins = "*")
public class KategorijaController {

    private final KategorijaRepository kategorijaRepository;

    public KategorijaController(KategorijaRepository kategorijaRepository) {
        this.kategorijaRepository = kategorijaRepository;
    }

    @GetMapping
    public List<Kategorija> vrniVse() {
        return kategorijaRepository.findAll();
    }
}
