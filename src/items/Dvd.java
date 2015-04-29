package items;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
/**
 *
 * @author Yannick
 */
@Entity
public class Dvd extends Item implements Serializable {

    public Dvd() {
        super();
    }

    public Dvd(String titel, String leeftijd, List<String> themas, String aantal) {
        super(titel, leeftijd, themas, aantal);

    }

    @Override
    public String toString() {
        return getNaam();
    }

}
