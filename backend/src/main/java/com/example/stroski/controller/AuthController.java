package com.example.stroski.controller;

import com.example.stroski.dto.RegistracijaZahteva;
import com.example.stroski.dto.UporabnikOdgovor;
import com.example.stroski.model.Uporabnik;
import com.example.stroski.service.UporabnikService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UporabnikService uporabnikService;

    public AuthController(UporabnikService uporabnikService) {
        this.uporabnikService = uporabnikService;
    }

    @PostMapping("/registracija")
    public ResponseEntity<UporabnikOdgovor> registracija(@Valid @RequestBody RegistracijaZahteva zahteva) {
        Uporabnik uporabnik = uporabnikService.registriraj(zahteva);
        return ResponseEntity.status(HttpStatus.CREATED).body(UporabnikOdgovor.iz(uporabnik));
    }
}
