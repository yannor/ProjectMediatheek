package items;

import java.io.Serializable;
import java.util.List;
import javafx.collections.*;
import javax.persistence.*;

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
    
    @Access(AccessType.PROPERTY)
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    public List<Exemplaar> getItems() {
	return exInhoud;
    }
   
    @Transient
    public ObservableList<Exemplaar> getExInhoud() {
        return exInhoud;
    }

    public void setItems(List<Exemplaar> exemp) {
	this.exInhoud = FXCollections.observableArrayList(exemp);
    }
    
     

    @Override
    public String toString() {
        return getNaam();
    }

    

    
    
}
