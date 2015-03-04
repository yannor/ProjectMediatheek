
package items;

public class Dvd extends Item {

private int min;

    public Dvd(int id, String naam, String thema, String leeftijd, int aantal, int min) {
        super(id, naam, thema, leeftijd, aantal);
        this.min=min;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

  

}
