package item;




public class Spel extends Item{

    String [] inhoud;
    String [] richtlijnen; //leeftijd, aantal spelers,...
    
    public Spel(String titel, int id, int aantalExemplaren, String beschrijving, String thema) {
        super(titel, id, aantalExemplaren, beschrijving, thema);
        
        
    }
    
}
