package view;

import javax.swing.*;
import java.awt.*;
import model.Iscritto;
import model.PianoAllenamento;
import model.PianoAlimentare;
import controller.FileManager;

public class PianoEditorFrame extends JFrame {

    public PianoEditorFrame(Iscritto iscritto, boolean allenamento) {
        setTitle(allenamento ? "Modifica Piano Allenamento" : "Modifica Piano Alimentare");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 15));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        // Carica descrizione attuale
        if (allenamento) {
            PianoAllenamento piano = iscritto.getPianoAllenamento();
            if (piano != null) {
                area.setText(piano.getDescrizione());
            } else {
                area.setText("Nessun piano assegnato.");
            }
        } else {
            PianoAlimentare piano = iscritto.getPianoAlimentare();
            if (piano != null) {
                area.setText(piano.getDescrizione());
            } else {
                area.setText("Nessun piano assegnato.");
            }
        }

        JButton btnSalva = new JButton("Salva modifiche");
        btnSalva.setFont(new Font("Arial", Font.BOLD, 15));
        btnSalva.addActionListener(e -> {
            String testo = area.getText().trim();

            if (testo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Il piano non può essere vuoto.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (allenamento) {
                PianoAllenamento nuovo = new PianoAllenamento("Personalizzato", testo, null, "Custom");
                iscritto.setPianoAllenamento(nuovo);
                FileManager.salvaAllenamento(iscritto.getEmail(), testo);
            } else {
                PianoAlimentare nuovo = new PianoAlimentare("Personalizzato", testo, null, 2000);
                iscritto.setPianoAlimentare(nuovo);
                FileManager.salvaAlimentazione(iscritto.getEmail(), testo);
            }

            JOptionPane.showMessageDialog(this, "Piano salvato correttamente.", "Successo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        add(new JScrollPane(area), BorderLayout.CENTER);
        add(btnSalva, BorderLayout.SOUTH);
        setVisible(true);
    }
}
