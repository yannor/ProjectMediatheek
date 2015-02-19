package item;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Verteltas extends Item{
    private List<Item> items;
    String leesniveau;

    public Verteltas(String titel, int id, int aantalExemplaren, String beschrijving, String thema, Item[]array, String leesnivea) {
        super(titel, id, aantalExemplaren, beschrijving, thema);
        items = new ArrayList<>(Arrays.asList(array));
        this.leesniveau=leesnivea;
    }
    
    public void voegItemToe(Item item){
        items.add(item);
    }
    
    public void verwijderItem(Item item){
        items.remove(item);
    }
    
}
