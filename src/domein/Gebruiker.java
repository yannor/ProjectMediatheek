package domein;

import java.io.Serializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.*;
/**
 *
 * @author Yannick
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "GEBRUIKERS")
@NamedQueries({
    @NamedQuery(name = "Gebruiker.findAll", query = "SELECT g FROM Gebruiker g")
})
public class Gebruiker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.FIELD)
    private int id;

    private final StringProperty naam = new SimpleStringProperty();
    private final StringProperty voorNaam = new SimpleStringProperty();
    private final StringProperty klas = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final ObjectProperty<TypeGebruiker> type = new SimpleObjectProperty<>();
    private String wachtwoord;

    public Gebruiker() {
    }

    public Gebruiker(String naam, String voorNaam, String klas, String email, TypeGebruiker type, String wachtwoord) {
        this.naam.set(naam);
        this.voorNaam.set(voorNaam);
        this.klas.set(klas);
        this.email.set(email);
        this.type.set(type);
        this.wachtwoord = wachtwoord;
    }

    public int getId() {
        return id;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public StringProperty naamProperty() {
        return naam;
    }

    public String getNaam() {
        return naam.get();
    }

    public void setNaam(String naam) {
        this.naam.set(naam);
    }

    public StringProperty voorNaamProperty() {
        return voorNaam;
    }

    public String getVoorNaam() {
        return voorNaam.get();
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam.set(voorNaam);
    }

    public StringProperty klasProperty() {
        return klas;
    }

    public String getKlas() {
        return klas.get();
    }

    public void setKlas(String klas) {
        this.klas.set(klas);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public ObjectProperty<TypeGebruiker> typeGebruikerProperty() {
        return type;
    }

    public TypeGebruiker getTypeGebruiker() {
        return type.get();
    }

    public void setTypeGebruiker(TypeGebruiker type) {
        this.type.set(type);
    }

}
