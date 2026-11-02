package com.example.stroski.controller;

import com.example.stroski.model.Strosek;
import com.example.stroski.service.StrosekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RestController
@RequestMapping("/api/stroski")
@CrossOrigin(origins = "*")
public class StrosekController {

    @Autowired
    private StrosekService strosekService;

    @GetMapping
    public List<Strosek> pridobiVseStroske(
            @RequestParam(required = false) String naziv,//ni potrebno 
            @RequestParam(required = false) Long kategorijaId) {
        return strosekService.vrniVseStroske(naziv, kategorijaId);
    }


    @GetMapping("/{id}")// frontenda
    public ResponseEntity<Strosek> pridobiStrosekPoId(@PathVariable Long id) {
        return strosekService.vrniStrosekPoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Strosek dodajStrosek(@RequestBody Strosek strosek) {
        return strosekService.dodajStrosek(strosek);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Strosek> posodobiStrosek(@PathVariable Long id, @RequestBody Strosek strosek) {
        try {
            Strosek posodobljen = strosekService.posodobiStrosek(id, strosek);
            return ResponseEntity.ok(posodobljen);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> izbrisiStrosek(@PathVariable Long id) {
        strosekService.izbrisiStrosek(id);
        return ResponseEntity.noContent().build();
    }
}
