package items;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Dvd extends Item implements Serializable {

    public Dvd() {
        super();
    }

    public Dvd(String titel, String leeftijd, List<String> themas) {
        super(titel, leeftijd, themas);

    }

    @Override
    public String toString() {
        return getNaam();
    }

}
