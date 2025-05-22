package model;

public class Iscritto extends Persona {
    private int età;
    private double peso;
    private double altezza;
    private PianoAllenamento pianoAllenamento;
    private PianoAlimentare pianoAlimentare;

    public Iscritto(String nome, String cognome, String email, int età, double peso, double altezza) {
        super(nome, cognome, email);
        this.età = età;
        this.peso = peso;
        this.altezza = altezza;
    }

    public double calcolaBMI() {
        return peso / (altezza * altezza);
    }

    public String getDettagli() {
        return getNomeCompleto() + " - BMI: " + String.format("%.2f", calcolaBMI());
    }

    public PianoAllenamento getPianoAllenamento() {
        return pianoAllenamento;
    }

    public void setPianoAllenamento(PianoAllenamento pianoAllenamento) {
        this.pianoAllenamento = pianoAllenamento;
    }

    public PianoAlimentare getPianoAlimentare() {
        return pianoAlimentare;
    }

    public void setPianoAlimentare(PianoAlimentare pianoAlimentare) {
        this.pianoAlimentare = pianoAlimentare;
    }

    // Getter aggiunti
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getEtà() {
        return età;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltezza() {
        return altezza;
    }

    public void setEtà(int età) {
        this.età = età;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }
}