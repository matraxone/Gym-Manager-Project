package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import controller.FileManager;
import model.Iscritto;

public class MainGUI {
    public static void main(String[] args) {
        List<Iscritto> iscritti = FileManager.caricaIscritti("dati/iscritti.csv");
        FileManager.caricaPianiAllenamento(iscritti, "dati/allenamenti.csv");
        FileManager.caricaPianiAlimentari(iscritti, "dati/alimentazione.csv");

        JFrame frame = new JFrame("Gym Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        DefaultListModel<Iscritto> model = new DefaultListModel<>();
        for (Iscritto i : iscritti) {
            model.addElement(i);
        }

        JList<Iscritto> lista = new JList<>(model);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getDettagli());
            label.setFont(new Font("Arial", Font.PLAIN, 16));
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
                label.setOpaque(true);
            }
            return label;
        });

        lista.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Iscritto selezionato = lista.getSelectedValue();
                    if (selezionato != null) {
                        new SceltaPianoFrame(selezionato);
                    }
                }
            }
        });

        frame.add(new JScrollPane(lista), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
