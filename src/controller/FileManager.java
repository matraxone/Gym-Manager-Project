package controller;

import java.io.*;
import java.util.*;
import model.Iscritto;
import model.PianoAllenamento;
import model.PianoAlimentare;

public class FileManager {

    public static List<Iscritto> caricaIscritti(String path) {
        List<Iscritto> iscritti = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",");
                Iscritto i = new Iscritto(campi[0], campi[1], campi[2],
                        Integer.parseInt(campi[3]),
                        Double.parseDouble(campi[4]),
                        Double.parseDouble(campi[5]));
                iscritti.add(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iscritti;
    }

    public static void caricaPianiAllenamento(List<Iscritto> iscritti, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",", 2);
                if (campi.length < 2) continue;

                String email = campi[0].trim();
                String descrizione = campi[1].trim();

                for (Iscritto i : iscritti) {
                    if (i.getEmail().equalsIgnoreCase(email)) {
                        i.setPianoAllenamento(new PianoAllenamento("Importato", descrizione, null, "Import"));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Errore lettura allenamenti.csv");
        }
    }

    public static void caricaPianiAlimentari(List<Iscritto> iscritti, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",", 2);
                if (campi.length < 2) continue;

                String email = campi[0].trim();
                String descrizione = campi[1].trim();

                for (Iscritto i : iscritti) {
                    if (i.getEmail().equalsIgnoreCase(email)) {
                        i.setPianoAlimentare(new PianoAlimentare("Importato", descrizione, null, 2000));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Errore lettura alimentazione.csv");
        }
    }

    public static void salvaAllenamento(String email, String descrizione) {
        salvaRigaSuCSV("dati/allenamenti.csv", email, descrizione);
    }

    public static void salvaAlimentazione(String email, String descrizione) {
        salvaRigaSuCSV("dati/alimentazione.csv", email, descrizione);
    }

    private static void salvaRigaSuCSV(String path, String email, String descrizione) {
        Map<String, String> dati = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",", 2);
                if (campi.length == 2)
                    dati.put(campi[0], campi[1]);
            }
        } catch (IOException e) {
        }

        dati.put(email, descrizione.replaceAll("\n", " "));

        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (String key : dati.keySet()) {
                pw.println(key + "," + dati.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
