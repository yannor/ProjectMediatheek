package items;

import java.io.Serializable;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
/**
 *
 * @author Yannick
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Verteltas.findAll", query = "SELECT i FROM Verteltas i")
})
public class Verteltas extends Item implements Serializable {

    private ObservableList<Exemplaar> exInhoud = FXCollections.observableArrayList();

    public Verteltas() {
    }

    public Verteltas(String titel, String leeftijd, List<String> themas, String aantal) {
        super(titel, leeftijd, themas, aantal);
    }

    public void addItem(Exemplaar item) {
        exInhoud.add(item);
    }

    public void removeItem(Exemplaar item) {
        exInhoud.remove(item);
    }
    

   
    
    public ObservableList<Exemplaar> getExInhoud() {
        return exInhoud;
    }

    public void setExInhoud(ObservableList<Exemplaar> exInhoud) {
        this.exInhoud = exInhoud;
    }
    
     

    @Override
    public String toString() {
        return getNaam();
    }

    

    
    
}
