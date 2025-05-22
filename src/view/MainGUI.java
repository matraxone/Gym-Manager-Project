package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import controller.FileManager;
import model.Iscritto;

public class MainGUI {
    private static List<Iscritto> iscritti;
    private static DefaultListModel<Iscritto> model;
    private static JList<Iscritto> lista;
    private static JLabel infoLabel;

    public static void main(String[] args) {
        iscritti = FileManager.caricaIscritti("dati/iscritti.csv");
        FileManager.caricaPianiAllenamento(iscritti, "dati/allenamenti.csv");
        FileManager.caricaPianiAlimentari(iscritti, "dati/alimentazione.csv");

        JFrame frame = new JFrame("Gym Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);

        model = new DefaultListModel<>();
        for (Iscritto i : iscritti) {
            model.addElement(i);
        }

        lista = new JList<>(model);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getDettagli());
            label.setFont(new Font("Arial", Font.PLAIN, 16));
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
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

        // Panel per la lista
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Clienti Registrati (Doppio click per modificare piani)"));
        listPanel.add(new JScrollPane(lista), BorderLayout.CENTER);

        // Panel per i pulsanti
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton aggiungiButton = new JButton("Aggiungi Cliente");
        aggiungiButton.setFont(new Font("Arial", Font.BOLD, 14));
        aggiungiButton.setPreferredSize(new Dimension(150, 35));
        aggiungiButton.addActionListener(e -> aggiungiCliente(frame));

        JButton eliminaButton = new JButton("Elimina Cliente");
        eliminaButton.setFont(new Font("Arial", Font.BOLD, 14));
        eliminaButton.setPreferredSize(new Dimension(150, 35));
        eliminaButton.addActionListener(e -> eliminaCliente(frame));

        buttonPanel.add(aggiungiButton);
        buttonPanel.add(eliminaButton);

        // Layout principale
        frame.setLayout(new BorderLayout());
        frame.add(listPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Info panel in alto
        infoLabel = new JLabel("Gestione Clienti Palestra - Totale clienti: " + iscritti.size());
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(infoLabel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private static void aggiungiCliente(JFrame parent) {
        AggiungiClienteDialog dialog = new AggiungiClienteDialog(parent);
        dialog.setVisible(true);

        if (dialog.isClienteAggiunto()) {
            Iscritto nuovoIscritto = dialog.getNuovoIscritto();

            // Controlla se l'email esiste già
            if (FileManager.emailEsiste(nuovoIscritto.getEmail(), "dati/iscritti.csv")) {
                JOptionPane.showMessageDialog(parent,
                        "Un cliente con questa email esiste già!",
                        "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aggiungi alla lista e al file
            iscritti.add(nuovoIscritto);
            model.addElement(nuovoIscritto);
            FileManager.salvaIscritti(iscritti, "dati/iscritti.csv");

            // Aggiorna contatore
            infoLabel.setText("Gestione Clienti Palestra - Totale clienti: " + iscritti.size());

            JOptionPane.showMessageDialog(parent,
                    "Cliente " + nuovoIscritto.getNomeCompleto() + " aggiunto con successo!",
                    "Successo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void eliminaCliente(JFrame parent) {
        Iscritto selezionato = lista.getSelectedValue();

        if (selezionato == null) {
            JOptionPane.showMessageDialog(parent,
                    "Seleziona un cliente da eliminare dalla lista!",
                    "Nessuna selezione", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int conferma = JOptionPane.showConfirmDialog(parent,
                "Sei sicuro di voler eliminare il cliente:\n" + selezionato.getNomeCompleto() + "?\n\n" +
                        "Questa operazione eliminerà anche i suoi piani di allenamento e alimentazione.",
                "Conferma eliminazione",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (conferma == JOptionPane.YES_OPTION) {
            // Rimuovi dalla lista e dal file
            iscritti.remove(selezionato);
            model.removeElement(selezionato);

            // Elimina da tutti i file CSV
            FileManager.eliminaIscrittoCSV(selezionato.getEmail(), "dati/iscritti.csv");
            FileManager.eliminaAllenamentoCSV(selezionato.getEmail());
            FileManager.eliminaAlimentazioneCSV(selezionato.getEmail());

            // Aggiorna contatore
            infoLabel.setText("Gestione Clienti Palestra - Totale clienti: " + iscritti.size());

            JOptionPane.showMessageDialog(parent,
                    "Cliente " + selezionato.getNomeCompleto() + " eliminato con successo!",
                    "Eliminazione completata", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}