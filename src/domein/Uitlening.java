package domein;

import java.io.Serializable;
import java.util.Calendar;
import javafx.beans.property.*;

import javax.persistence.*;
/**
 *
 * @author Yannick
 */
@Entity
@Access(AccessType.PROPERTY)
@NamedQueries({
    @NamedQuery(name = "Uitlening.findAll", query = "SELECT u FROM Uitlening u")
})
public class Uitlening implements Serializable {

    private int id;

    private final ObjectProperty<items.Exemplaar> exemplaar = new SimpleObjectProperty<>();
    private final ObjectProperty<Gebruiker> gebruiker = new SimpleObjectProperty<>();
   
   // private final ObjectProperty<Calendar> datum = new SimpleObjectProperty<>();
    private final BooleanProperty terug = new SimpleBooleanProperty(false);
    
    
     private final ObjectProperty<Calendar> uitleenDatum = new SimpleObjectProperty<>();

    public Uitlening() {
    }

    public Uitlening(items.Exemplaar ex, Gebruiker gebr) {
	this.exemplaar.set(ex);
	this.gebruiker.set(gebr);
	uitleenDatum.set(Calendar.getInstance());
	
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }
    
      public ObjectProperty<items.Exemplaar> ExemplaarProperty() {
	return exemplaar;
    }
    @ManyToOne
    public items.Exemplaar getExemplaar() {
	return exemplaar.get();
    }

    public void setExemplaar(items.Exemplaar ex) {
	this.exemplaar.set(ex);
    }

 public ObjectProperty<Gebruiker> gebruikerProperty() {
	return this.gebruiker;
    }
    @ManyToOne
    public Gebruiker getGebruiker() {
	return gebruiker.get();
    }

    public void setGebruiker(Gebruiker gebruiker) {
	this.gebruiker.set(gebruiker);
    }

    
    public BooleanProperty terugProperty() {
	return this.terug;
    }
    public boolean getTerug() {
	return terug.get();
    }

    public void setTerug(boolean isTerug) {
	this.terug.set(isTerug);
    }


    
     @Temporal(TemporalType.DATE)
    public Calendar getUitleenDatum() {
	return this.uitleenDatum.get();
    }

    public void setUitleenDatum(Calendar datum) {
	this.uitleenDatum.set(datum);
    }

    public ObjectProperty<Calendar> uitleenDatumProperty() {
	return this.uitleenDatum;
    }


}
