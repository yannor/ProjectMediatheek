/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package items;

import java.util.ArrayList;
import java.util.List;


public class Verteltas extends Item{
    private List<Item> items = new ArrayList<Item>();

    public Verteltas(int id, String naam, String thema, String leeftijd, int aantal) {
        super(id, naam, thema, leeftijd, aantal);
    }
    
    public void voegItemToe(Item item){
        items.add(item);
    }
    
    public void verwijderItem(Item item){
        items.remove(item);
    }
}
