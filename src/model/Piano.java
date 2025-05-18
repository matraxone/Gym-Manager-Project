package model;

import java.util.List;

public abstract class Piano {
    protected String nome, descrizione;
    protected List<String> attività;

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
