package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Iscritto;

public class AggiungiClienteDialog extends JDialog {
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField emailField;
    private JTextField etàField;
    private JTextField pesoField;
    private JTextField altezzaField;
    private boolean clienteAggiunto = false;
    private Iscritto nuovoIscritto = null;

    public AggiungiClienteDialog(JFrame parent) {
        super(parent, "Aggiungi Nuovo Cliente", true);
        initializeComponents();
        setupLayout();
        setupActions();

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initializeComponents() {
        nomeField = new JTextField(20);
        cognomeField = new JTextField(20);
        emailField = new JTextField(20);
        etàField = new JTextField(20);
        pesoField = new JTextField(20);
        altezzaField = new JTextField(20);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nome
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(nomeField, gbc);

        // Cognome
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Cognome:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(cognomeField, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(emailField, gbc);

        // Età
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Età:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(etàField, gbc);

        // Peso
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Peso (kg):"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(pesoField, gbc);

        // Altezza
        gbc.gridx = 0; gbc.gridy = 5; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Altezza (m):"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(altezzaField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Pulsanti
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton aggiungiButton = new JButton("Aggiungi Cliente");
        JButton annullaButton = new JButton("Annulla");

        aggiungiButton.setFont(new Font("Arial", Font.BOLD, 14));
        annullaButton.setFont(new Font("Arial", Font.PLAIN, 14));

        buttonPanel.add(aggiungiButton);
        buttonPanel.add(annullaButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Aggiungi action listeners
        aggiungiButton.addActionListener(e -> aggiungiCliente());
        annullaButton.addActionListener(e -> dispose());
    }

    private void setupActions() {
        // Permettere di premere Enter per aggiungere
        getRootPane().setDefaultButton((JButton) ((JPanel) getContentPane().getComponent(1)).getComponent(0));
    }

    private void aggiungiCliente() {
        try {
            // Validazione campi
            String nome = nomeField.getText().trim();
            String cognome = cognomeField.getText().trim();
            String email = emailField.getText().trim();
            String etàText = etàField.getText().trim();
            String pesoText = pesoField.getText().trim();
            String altezzaText = altezzaField.getText().trim();

            if (nome.isEmpty() || cognome.isEmpty() || email.isEmpty() ||
                    etàText.isEmpty() || pesoText.isEmpty() || altezzaText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tutti i campi sono obbligatori!",
                        "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validazione email
            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, "Inserire un indirizzo email valido!",
                        "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Conversione numerica
            int età = Integer.parseInt(etàText);
            double peso = Double.parseDouble(pesoText);
            double altezza = Double.parseDouble(altezzaText);

            // Validazione valori numerici
            if (età <= 0 || età > 120) {
                JOptionPane.showMessageDialog(this, "L'età deve essere tra 1 e 120 anni!",
                        "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (peso <= 0 || peso > 500) {
                JOptionPane.showMessageDialog(this, "Il peso deve essere tra 1 e 500 kg!",
                        "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (altezza <= 0 || altezza > 3) {
                JOptionPane.showMessageDialog(this, "L'altezza deve essere tra 0 e 3 metri!",
                        "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Creazione nuovo iscritto
            nuovoIscritto = new Iscritto(nome, cognome, email, età, peso, altezza);
            clienteAggiunto = true;
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserire valori numerici validi per età, peso e altezza!",
                    "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isClienteAggiunto() {
        return clienteAggiunto;
    }

    public Iscritto getNuovoIscritto() {
        return nuovoIscritto;
    }
}