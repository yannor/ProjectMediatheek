package items;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.*;
import javafx.collections.*;
import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Cd extends Item implements Serializable {

    
    private final StringProperty beschrijving = new SimpleStringProperty();

    public Cd() {
        super();
    }

    public Cd(String titel, String leeftijd, List<String> themas, String beschr) {
        super(titel, leeftijd, themas);
        setBeschrijving(beschr);
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
        return getNaam() ;
    }

}
