
package item;

import db.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemBeheer {

private ObservableList<Item> items;
DbConnect connect= new DbConnect();

public ItemBeheer() {

items= FXCollections.observableArrayList();  
}

public ObservableList<Item> getItems() {
  
items.addAll(connect.getAlleSpellen());
items.addAll(connect.getAlleBoeken());
return items;
}





public boolean noItems() {
return items.isEmpty();
}
public void addBoek(String naam, String isbn, String thema, int aantalExemplaren, String auteur, int aantalBlz,String leesniveau, String bes) {

connect.addBoek( naam,  isbn,  thema,  aantalExemplaren,  auteur,  aantalBlz, leesniveau,  bes);

}

public void addSpel(String naam, int aantalExemplaren, String bes, String inhoud, String thema) {

connect.addSpel( naam,  aantalExemplaren,  bes, inhoud, thema);

}


public void addVerteltas(String naam, int aantalExemplaren, String bes, String thema, String niveau) {

connect.addVerteltas( naam,  aantalExemplaren,  bes, thema, niveau);

}


public void deleteBoek(int id) {

connect.deleteBoek(id);
}

public void deleteSpel(int id) {

connect.deleteBoek(id);
}

public void deleteVerteltas(int id) {

connect.deleteBoek(id);
}

public void updateBoek(int id, String naam, String isbn, String thema, int aantalExemplaren, String auteur, int aantalBlz,String leesniveau, String bes) {

connect.updateBoek( id,  naam,  isbn,  thema,  aantalExemplaren,  auteur,  aantalBlz, leesniveau,  bes);
}

public void updateSpel(int id, String naam, int aantalExemplaren, String bes, String inhoud, String thema) {

connect.updateSpel( id,  naam,  aantalExemplaren,  bes, inhoud, thema);
}

public void updateVerteltas(int id, String naam, int aantalExemplaren, String bes, String thema, String niveau) {

connect.updateVerteltas( id,  naam,  aantalExemplaren,  bes, thema, niveau);
}
}