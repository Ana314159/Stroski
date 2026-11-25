package com.example.stroski.dto;

import com.example.stroski.model.Uporabnik;
import java.time.LocalDateTime;

/** Podatki o uporabniku, ki jih vrnemo odjemalcu (brez gesla in njegovega hasha). */
public record UporabnikOdgovor(
        Long id,
        String uporabniskoIme,
        String email,
        LocalDateTime datumRegistracije,
        String vloga
) {
    public static UporabnikOdgovor iz(Uporabnik u) {
        return new UporabnikOdgovor(
                u.getId(), u.getUporabniskoIme(), u.getEmail(),
                u.getDatumRegistracije(), u.getVloga());
    }
}
