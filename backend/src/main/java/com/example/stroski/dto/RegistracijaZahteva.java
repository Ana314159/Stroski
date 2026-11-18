package com.example.stroski.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/** Podatki, ki jih odjemalec pošlje ob registraciji. */
public record RegistracijaZahteva(

        @NotBlank(message = "Uporabniško ime je obvezno.")
        @Size(min = 3, max = 30, message = "Uporabniško ime mora imeti od 3 do 30 znakov.")
        @Pattern(regexp = "^[A-Za-z0-9_.-]*$",
                 message = "Uporabniško ime lahko vsebuje le črke (brez šumnikov), številke in znake _ . -")
        String uporabniskoIme,

        @NotBlank(message = "E-naslov je obvezen.")
        @Size(max = 100, message = "E-naslov je predolg.")
        @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "E-naslov ni veljaven.")
        String email,

        @NotBlank(message = "Geslo je obvezno.")
        @Size(min = 8, max = 72, message = "Geslo mora imeti od 8 do 72 znakov.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).*$",
                 message = "Geslo mora vsebovati vsaj eno črko in eno številko.")
        String geslo
) {}
