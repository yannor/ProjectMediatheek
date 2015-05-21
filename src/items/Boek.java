package items;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.*;
import javax.persistence.*;


@Entity
@Access(AccessType.PROPERTY)
public class Boek extends Item implements Serializable {

    private StringProperty auteur = new SimpleStringProperty();
    private StringProperty uitgever = new SimpleStringProperty();
    private final StringProperty beschrijving = new SimpleStringProperty();

    public Boek() {
        super();
    }

    public Boek(String titel, String leeftijd, List<String> themas, String auteur, String uitgever, String beschrijving) {
        super(titel, leeftijd, themas);
        setAuteur(auteur);
        setUitgever(uitgever);
        setBeschrijving(beschrijving);
    }

    public StringProperty auteurProperty() {
        return auteur;
    }

    public String getAuteur() {
        return auteur.get();
    }

    public void setAuteur(String auteur) {
        this.auteur.set(auteur);
    }

    public StringProperty uitgeverProperty() {
        return uitgever;
    }

    public String getUitgever() {
        return uitgever.get();
    }

    public void setUitgever(String uitgever) {
        this.uitgever.set(uitgever);
    }

    public StringProperty beschrijvingProperty() {
        return beschrijving;
    }

    public String getBeschrijving() {
        return beschrijving.get();
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving.set(beschrijving);
    }

    @Override
    public String toString() {
        return getNaam() + " (" + getAuteur() + ")";
    }
}
