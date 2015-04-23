package items;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Entity;

@Entity
public class Puzzel extends Item implements Serializable {

    private StringProperty uitgever = new SimpleStringProperty();
    private final StringProperty stukken = new SimpleStringProperty();

    public Puzzel() {
        super();
    }

    public Puzzel(String titel, String leeftijd, List<String> themas, String uitgever, String stukken) {
        super(titel, leeftijd, themas);
        setStukken(stukken);
        setUitgever(uitgever);

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

    public StringProperty stukkenProperty() {
        return stukken;
    }

    public String getStukken() {
        return stukken.get();
    }

    public void setStukken(String stukken) {
        this.stukken.set(stukken);
    }

    @Override
    public String toString() {
        return getNaam();
    }
}
