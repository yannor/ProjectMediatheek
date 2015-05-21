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

    private ObservableList<Item> exInhoud = FXCollections.observableArrayList();

    public Verteltas() {
    }

    public Verteltas(String titel, String leeftijd, List<String> themas) {
        super(titel, leeftijd, themas);
    }

    public void addItem(Item ex) {
        exInhoud.add(ex);
    }

    public void removeItem(Item ex) {
        exInhoud.remove(ex);
    }
    
    @Access(AccessType.PROPERTY)
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    public List<Item> getItems() {
	return exInhoud;
    }
   
    @Transient
    public ObservableList<Item> getExInhoud() {
        return exInhoud;
    }

    public void setItems(List<Item> exemp) {
	this.exInhoud = FXCollections.observableArrayList(exemp);
    }
    
     

    @Override
    public String toString() {
        return getNaam();
    }

    

    
    
}
