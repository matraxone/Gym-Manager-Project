package model;

public abstract class Persona {
    protected String nome, cognome, email;

    public Persona(String nome, String cognome, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public String getNomeCompleto() {
        return nome + " " + cognome;
    }

    public String getEmail() {
        return email;
    }
}
