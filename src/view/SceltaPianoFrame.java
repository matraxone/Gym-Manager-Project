package view;

import javax.swing.*;
import java.awt.*;
import model.Iscritto;

public class SceltaPianoFrame extends JFrame {
    public SceltaPianoFrame(Iscritto iscritto) {
        setTitle("Seleziona piano per " + iscritto.getNomeCompleto());
        setSize(350, 160);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel label = new JLabel("Scegli un piano da modificare:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnAllenamento = new JButton("Piano Allenamento");
        btnAllenamento.setFont(new Font("Arial", Font.PLAIN, 16));
        btnAllenamento.addActionListener(e -> new PianoEditorFrame(iscritto, true));

        JButton btnAlimentare = new JButton("Piano Alimentare");
        btnAlimentare.setFont(new Font("Arial", Font.PLAIN, 16));
        btnAlimentare.addActionListener(e -> new PianoEditorFrame(iscritto, false));

        add(label);
        add(btnAllenamento);
        add(btnAlimentare);

        setVisible(true);
    }
}
