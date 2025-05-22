# Gym Manager Project

## 🇮🇹 Italiano

### Descrizione
**Gym Manager** è un'applicazione desktop sviluppata in Java con interfaccia grafica Swing per la gestione di una palestra. Il software permette di amministrare i clienti iscritti e i loro rispettivi piani di allenamento e alimentazione in modo semplice ed efficace.

### Caratteristiche Principali
- **Gestione Clienti**: Aggiungi, visualizza ed elimina clienti con i loro dati anagrafici (nome, cognome, email, età, peso, altezza)
- **Calcolo BMI**: Calcolo automatico dell'Indice di Massa Corporea per ogni cliente
- **Piani Personalizzati**: Creazione e modifica di piani di allenamento e alimentazione personalizzati
- **Persistenza Dati**: Salvataggio automatico su file CSV per mantenere i dati tra le sessioni
- **Interfaccia Intuitiva**: GUI user-friendly con operazioni semplici come doppio click per modificare

### Tecnologie Utilizzate
- **Java 24** (OpenJDK)
- **Swing** per l'interfaccia grafica
- **File CSV** per la persistenza dei dati
- **Architettura MVC** (Model-View-Controller)

### Struttura del Progetto
```
src/
├── controller/
│   └── FileManager.java          # Gestione file CSV
├── model/
│   ├── Persona.java             # Classe base astratta
│   ├── Iscritto.java           # Modello cliente
│   ├── Piano.java              # Classe base per i piani
│   ├── PianoAllenamento.java   # Piano di allenamento
│   └── PianoAlimentare.java    # Piano alimentare
└── view/
    ├── MainGUI.java            # Interfaccia principale
    ├── AggiungiClienteDialog.java  # Dialog per aggiungere clienti
    ├── SceltaPianoFrame.java   # Frame per scegliere tipo di piano
    └── PianoEditorFrame.java   # Editor per i piani

dati/
├── iscritti.csv        # Database clienti
├── allenamenti.csv     # Piani di allenamento
└── alimentazione.csv   # Piani alimentari
```

### Come Utilizzare
1. **Avvio**: Esegui la classe `MainGUI` per avviare l'applicazione
2. **Aggiungi Cliente**: Clicca "Aggiungi Cliente" e compila tutti i campi richiesti
3. **Visualizza Clienti**: La lista principale mostra tutti i clienti con nome e BMI
4. **Modifica Piani**: Doppio click su un cliente per modificare i suoi piani
5. **Elimina Cliente**: Seleziona un cliente e clicca "Elimina Cliente"

### Funzionalità Avanzate
- **Validazione Dati**: Controlli automatici su email, età, peso e altezza
- **Prevenzione Duplicati**: Impedisce l'inserimento di clienti con email già esistenti
- **Eliminazione Sicura**: Rimuove automaticamente tutti i dati associati al cliente
- **Backup Automatico**: Tutti i cambiamenti vengono salvati immediatamente su CSV

### Requisiti di Sistema
- Java 24 o superiore
- Sistema operativo con supporto Swing (Windows, macOS, Linux)

---

## 🇬🇧 English

### Description
**Gym Manager** is a desktop application developed in Java with Swing GUI for gym management. The software allows you to manage registered clients and their respective workout and nutrition plans in a simple and effective way.

### Main Features
- **Client Management**: Add, view and delete clients with their personal data (name, surname, email, age, weight, height)
- **BMI Calculation**: Automatic Body Mass Index calculation for each client
- **Custom Plans**: Creation and modification of personalized workout and nutrition plans
- **Data Persistence**: Automatic saving to CSV files to maintain data between sessions
- **Intuitive Interface**: User-friendly GUI with simple operations like double-click to edit

### Technologies Used
- **Java 24** (OpenJDK)
- **Swing** for graphical interface
- **CSV Files** for data persistence
- **MVC Architecture** (Model-View-Controller)

### Project Structure
```
src/
├── controller/
│   └── FileManager.java          # CSV file management
├── model/
│   ├── Persona.java             # Abstract base class
│   ├── Iscritto.java           # Client model
│   ├── Piano.java              # Base class for plans
│   ├── PianoAllenamento.java   # Workout plan
│   └── PianoAlimentare.java    # Nutrition plan
└── view/
    ├── MainGUI.java            # Main interface
    ├── AggiungiClienteDialog.java  # Dialog to add clients
    ├── SceltaPianoFrame.java   # Frame to choose plan type
    └── PianoEditorFrame.java   # Editor for plans

data/
├── iscritti.csv        # Client database
├── allenamenti.csv     # Workout plans
└── alimentazione.csv   # Nutrition plans
```

### How to Use
1. **Launch**: Run the `MainGUI` class to start the application
2. **Add Client**: Click "Aggiungi Cliente" (Add Client) and fill in all required fields
3. **View Clients**: The main list shows all clients with name and BMI
4. **Edit Plans**: Double-click on a client to modify their plans
5. **Delete Client**: Select a client and click "Elimina Cliente" (Delete Client)

### Advanced Features
- **Data Validation**: Automatic checks on email, age, weight and height
- **Duplicate Prevention**: Prevents insertion of clients with already existing emails
- **Safe Deletion**: Automatically removes all data associated with the client
- **Automatic Backup**: All changes are immediately saved to CSV

### System Requirements
- Java 24 or higher
- Operating system with Swing support (Windows, macOS, Linux)

### Installation and Execution
1. Clone the repository
2. Compile Java files: `javac -d out src/**/*.java`
3. Run the application: `java -cp out view.MainGUI`
