
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
public void removeItem(Item item) {

connect.deleteItem(item.getId());
}

public void updateBoek(int id, String naam, String isbn, String thema, int aantalExemplaren, String auteur, int aantalBlz,String leesniveau, String bes) {

connect.updateBoek( id,  naam,  isbn,  thema,  aantalExemplaren,  auteur,  aantalBlz, leesniveau,  bes);
}
}