package item;

import java.util.ArrayList;
import java.util.Arrays;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Spel extends Item{

    String [] inhoud;
    String [] richtlijnen; //leeftijd, aantal spelers,...
    
    public Spel(String titel, int id, int aantalExemplaren, String beschrijving, String thema) {
        super(titel, id, aantalExemplaren, beschrijving, thema);
        
        
    }
    
}
