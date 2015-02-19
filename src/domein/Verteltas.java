package domein;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Verteltas extends Item{
    private List<Item> items = new ArrayList<Item>();

    public Verteltas(String titel, int nummer, int aantalExemplaren, String beschrijving, String thema) {
        super(titel, nummer, aantalExemplaren, beschrijving, thema);
    }
    
    public void voegItemToe(Item item){
        items.add(item);
    }
    
    public void verwijderItem(Item item){
        items.remove(item);
    }
    
}
