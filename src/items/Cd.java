package items;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
/**
 *
 * @author Yannick
 */
@Entity
@Access(AccessType.PROPERTY)
public class Cd extends Item implements Serializable {

    private StringProperty zanger = new SimpleStringProperty();
    private ObservableList<String> liedjes = FXCollections.observableArrayList();

    public Cd() {
        super();
    }

    public Cd(String titel, String leeftijd, List<String> themas, String zanger, List<String> liedjes, String aantal) {
        super(titel, leeftijd, themas, aantal);
        setZanger(zanger);
        setLiedjesLijst(liedjes);
    }

    public StringProperty zangerProperty() {
        return zanger;
    }

    public String getZanger() {
        return zanger.get();
    }

    public void setZanger(String zanger) {
        this.zanger.set(zanger);
    }

    public ObservableList<String> getLiedjes() {
        return liedjes;
    }

    public List<String> getLiedjesList() {
        return liedjes;
    }

    public void setLiedjesLijst(List<String> liedjesLijst) {
        this.liedjes = FXCollections.observableList(liedjesLijst);
    }

    @Override
    public String toString() {
        return getNaam() + " (" + getZanger() + ")";
    }

}
