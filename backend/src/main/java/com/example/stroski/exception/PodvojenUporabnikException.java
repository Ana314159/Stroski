package com.example.stroski.exception;

public class PodvojenUporabnikException extends RuntimeException {

    private final String polje; // polje obrazca, na katerega se napaka nanaša (lahko null)

    public PodvojenUporabnikException(String sporocilo, String polje) {
        super(sporocilo);
        this.polje = polje;
    }

    public String getPolje() {
        return polje;
    }
}
