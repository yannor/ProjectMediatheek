package items;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.*;
import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Spel extends Item implements Serializable {

    private StringProperty uitgeverij = new SimpleStringProperty();

    public Spel() {
        super();
    }

    public Spel(String titel, String leeftijd, List<String> themas, String uitgeverij) {
        super(titel, leeftijd, themas);
        setUitgeverij(uitgeverij);
    }

    public StringProperty uitgeverijProperty() {
        return uitgeverij;
    }

    public String getUitgeverij() {
        return uitgeverij.get();
    }

    public void setUitgeverij(String uitgeverij) {
        this.uitgeverij.set(uitgeverij);
    }

    @Override
    public String toString() {
        return getNaam();
    }

}
