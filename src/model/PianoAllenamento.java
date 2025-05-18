package model;

import java.util.List;

public class PianoAllenamento extends Piano {
    private String obiettivo;

    public PianoAllenamento(String nome, String descrizione, List<String> attività, String obiettivo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.attività = attività;
        this.obiettivo = obiettivo;
    }

    public String getObiettivo() {
        return obiettivo;
    }
}
