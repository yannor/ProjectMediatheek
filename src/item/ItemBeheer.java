
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
public void addItem(Item item) {
if (item != null) {
connect.addItem(item.getTitel(), item.getBeschrijving());
}
}
public void removeItem(Item item) {

connect.deleteItem(item.getId());
}

public void updateItem(int id,String tit, String bes) {

connect.updateItem(id,tit, bes);
}
}