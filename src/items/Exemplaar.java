package items;

import java.io.Serializable;
import javafx.beans.property.*;
import javax.persistence.*;
/**
 *
 * @author Yannick
 */
@Entity
@Access(AccessType.PROPERTY)
@NamedQueries({
    @NamedQuery(name = "Exemplaar.findAll", query = "SELECT e FROM Exemplaar e")
})
public class Exemplaar implements Serializable {

    private int id;
    private final ObjectProperty<Item> item = new SimpleObjectProperty<>();
    private final ObjectProperty<Beschikbaarheid> beschikbaar = new SimpleObjectProperty<>();
    private final StringProperty nr = new SimpleStringProperty();

    public Exemplaar() {
    }

    public Exemplaar(String nr, Item i, Beschikbaarheid b) {
        setNr(nr);
        setItem(i);
        setBeschikbaarheid(b);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StringProperty nrProperty() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr.set(nr);
    }

    public String getNr() {
        return this.nr.get();
    }

    public ObjectProperty<Item> itemProperty() {
        return item;
    }

    public void setItem(Item i) {
        this.item.set(i);
    }

    
    public Item getItem() {
        return this.item.get();
    }

    public ObjectProperty<Beschikbaarheid> beschikbaarheidProperty() {
        return beschikbaar;
    }

    @Enumerated(EnumType.STRING)
    public Beschikbaarheid getBeschikbaarheid() {
        return beschikbaar.get();
    }

    public void setBeschikbaarheid(Beschikbaarheid beschikbaar) {
        this.beschikbaar.set(beschikbaar);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        try {
            return this.getItem().getNaam() + " (Exemplaar #" + getNr() + ")";
        } catch (NullPointerException npex) {
            return "Exemplaar #" + getNr();
        }
    }

}
