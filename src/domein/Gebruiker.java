package domein;

import java.io.*;
import java.util.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javax.persistence.*;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "GEBRUIKERS")
@NamedQueries({
    @NamedQuery(name = "Gebruiker.findAll", query = "SELECT g FROM Gebruiker g")
})
public class Gebruiker implements Serializable {

    
    private int id;

    private final StringProperty naam = new SimpleStringProperty();
    private final StringProperty voorNaam = new SimpleStringProperty();
    private final StringProperty klas = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final ObjectProperty<TypeGebruiker> type = new SimpleObjectProperty<>();
    private final StringProperty straat = new SimpleStringProperty();
    private final StringProperty gemeente = new SimpleStringProperty();
    private final StringProperty postCode = new SimpleStringProperty();
    
    
   
    
    
    
    private final ObservableList<Uitlening> uitleningen= FXCollections.observableArrayList();
    
     public Gebruiker() {
       
    }
    


    public Gebruiker(String naam, String voorNaam, String klas, String email, TypeGebruiker type, String straat, String gemeente, String postCode) {
        this.naam.set(naam);
        this.voorNaam.set(voorNaam);
        this.klas.set(klas);
        this.email.set(email);
        this.type.set(type);
        
        this.straat.set(straat);
        this.gemeente.set(gemeente);
        this.postCode.set(postCode);
    }

    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    
     public void setId(int id) {
        this.id=id;
    }

  
    


    
    
    
    public String getStraat() {
        return straat.get();
    }
    
    public StringProperty straatProperty() {
        return straat;
    }

     public void setStraat(String straat) {
        this.straat.set(straat);
    }
    
    public String getGemeente() {
        return gemeente.get();
    }
    
    public StringProperty gemeenteProperty() {
        return gemeente;
    }

     public void setGemeente(String gemeente) {
        this.gemeente.set(gemeente);
    }
     
     public String getPostCode() {
        return postCode.get();
    }
    
    public StringProperty postCodeProperty() {
        return postCode;
    }

     public void setPostCode(String postCode) {
        this.postCode.set(postCode);
    }
    
     
    public StringProperty naamProperty() {
        return naam;
    }

    public String getNaam() {
        return naam.get();
    }

    public void setNaam(String naam) {
        this.naam.set(naam);
    }

    public StringProperty voorNaamProperty() {
        return voorNaam;
    }

    public String getVoorNaam() {
        return voorNaam.get();
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam.set(voorNaam);
    }

    public StringProperty klasProperty() {
        return klas;
    }

    public String getKlas() {
        return klas.get();
    }

    public void setKlas(String klas) {
        this.klas.set(klas);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public ObjectProperty<TypeGebruiker> typeGebruikerProperty() {
        return type;
    }

    public TypeGebruiker getTypeGebruiker() {
        return type.get();
    }

    public void setTypeGebruiker(TypeGebruiker type) {
        this.type.set(type);
    }
    
    
    
    @OneToMany(cascade = CascadeType.ALL)
    public List<Uitlening> getUitlening() {
	return this.uitleningen;
    }

    public void setUitlening(List<Uitlening> uitleningen) {
	this.uitleningen.setAll(uitleningen);
    }

    @Override
    public String toString() {
        return naam.get() +", "+ voorNaam.get() + " " + "(" + klas.get().trim()+")";
    }
    
    

 
 
    
}
