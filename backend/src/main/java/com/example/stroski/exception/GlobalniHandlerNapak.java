package com.example.stroski.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalniHandlerNapak {

    // 400: neveljavni podatki (validacija)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> obravnavajValidacijo(MethodArgumentNotValidException ex) {
        Map<String, String> napake = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> napake.putIfAbsent(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("sporocilo", "Podatki niso veljavni.", "napake", napake));
    }

    // 409: uporabniško ime ali e-naslov že obstaja
    @ExceptionHandler(PodvojenUporabnikException.class)
    public ResponseEntity<Map<String, Object>> obravnavajPodvojenega(PodvojenUporabnikException ex) {
        Map<String, String> napake = new HashMap<>();
        if (ex.getPolje() != null) {
            napake.put(ex.getPolje(), ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("sporocilo", ex.getMessage(), "napake", napake));
    }
}
