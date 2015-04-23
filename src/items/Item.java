package items;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.*;
import javafx.collections.*;
import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Serializable {

    private final StringProperty naam = new SimpleStringProperty();

    private final ObservableList<String> themas = FXCollections.observableArrayList();
    private final StringProperty leeftijd = new SimpleStringProperty();

    private List<Exemplaar> exemplaren;

    private int id;

    public Item() {
    }

    public Item(String naam, String leeftijd, List<String> themas) {
        setThemas(themas);
        setLeeftijd(leeftijd);
        setNaam(naam);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public StringProperty leeftijdProperty() {
        return leeftijd;
    }

    public String getLeeftijd() {
        return leeftijd.get();
    }

    public void setLeeftijd(String leeftijd) {
        this.leeftijd.set(leeftijd);
    }

    public ObservableList<String> getThemas() {
        return themas;
    }

    public List<String> getThemas2() {
        return themas;
    }

    public void setThemas(List<String> thema) {
        this.themas.setAll(thema);
    }

    public List<Exemplaar> getExemplaren() {
        return exemplaren;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return getNaam();
    }

    public static String eersteLetter(Item item) {
        if (item instanceof Boek) {
            return "B";
        } else if (item instanceof Dvd) {
            return "D";
        } else if (item instanceof Cd) {
            return "C";
        } else if (item instanceof Spel) {
            return "S";
        } else if (item instanceof Verteltas) {
            return "V";
        } else if (item instanceof Puzzel) {
            return "P";
        }
        return null;
    }
}
