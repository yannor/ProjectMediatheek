/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

/**
 *
 * @author Matthias
 */
public class Spel extends Item
{
    private String uitgeverij;

    public Spel(int id, String naam, String thema, String beschrijving, String leeftijd, int aantal, String uitgeverij) 
    {
        super(id, naam, thema,beschrijving, leeftijd, aantal);
        this.uitgeverij = uitgeverij;
    }

    public String getUitgeverij()
    {
        return uitgeverij;
    }

    public void setUitgeverij(String uitgeverij)
    {
        this.uitgeverij = uitgeverij;
    }
}
