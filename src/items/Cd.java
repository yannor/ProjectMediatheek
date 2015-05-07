package items;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.*;
import javafx.collections.*;
import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
public class Cd extends Item implements Serializable {

    
    private ObservableList<String> liedjes = FXCollections.observableArrayList();

    public Cd() {
        super();
    }

    public Cd(String titel, String leeftijd, List<String> themas, List<String> liedjes, String aantal) {
        super(titel, leeftijd, themas, aantal);
        setLiedjesLijst(liedjes);
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
        return getNaam() ;
    }

}
