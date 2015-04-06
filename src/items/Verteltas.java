/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package items;

import java.util.ArrayList;
import java.util.List;


public class Verteltas extends Item{
    private List<Item> items = new ArrayList<>();

    public Verteltas(int id, String naam, String thema, String beschrijving, String leeftijd, int aantal, List<Item> items) 
    {
        super(id, naam, thema, beschrijving, leeftijd, aantal);
        this.items=items;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public void setItems(List<Item> items)
    {
        this.items = items;
    }
    
    public void voegItemToe(Item item)
    {
        items.add(item);
    }
    
    public void verwijderItem(Item item)
    {
        items.remove(item);
    }
}
