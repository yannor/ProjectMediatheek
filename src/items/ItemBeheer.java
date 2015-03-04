




// helemaal herschrijven
package items;

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
  

return items;
}


public boolean noItems() {
return items.isEmpty();
}

}