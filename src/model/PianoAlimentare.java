package model;

import java.util.List;

public class PianoAlimentare extends Piano {
    private int calorieGiornaliere;

    public PianoAlimentare(String nome, String descrizione, List<String> attività, int calorie) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.attività = attività;
        this.calorieGiornaliere = calorie;
    }

    public int getCalorie() {
        return calorieGiornaliere;
    }
}
