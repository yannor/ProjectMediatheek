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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name = "Verteltas.findAll", query = "SELECT i FROM Verteltas i")
})
public class Verteltas extends Item implements Serializable {

    private ObservableList<Exemplaar> exInhoud = FXCollections.observableArrayList();

    public Verteltas() {
    }

    public Verteltas(String titel, String leeftijd, List<String> themas) {
        super(titel, leeftijd, themas);
    }

    public void addItem(Exemplaar item) {
        exInhoud.add(item);
    }

    public void removeItem(Exemplaar item) {
        exInhoud.remove(item);
    }

    @Transient
    public ObservableList<Exemplaar> getObservableInhoud() {
        return exInhoud;
    }

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    public List<Exemplaar> getInhoud() {
        return exInhoud;
    }

    public void setItems(List<Exemplaar> exInhoud) {
        this.exInhoud = FXCollections.observableArrayList(exInhoud);
    }

    @Override
    public String toString() {
        return getNaam();
    }

}
