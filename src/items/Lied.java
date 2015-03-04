
package items;

public class Lied {

    private int min;
    private int sec;
    private String zanger;
    private String naam;

    public Lied(String naam, String zanger, int min, int sec ) {
        this.min = min;
        this.sec = sec;
        this.zanger = zanger;
        this.naam = naam;
    }

    
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public String getZanger() {
        return zanger;
    }

    public void setZanger(String zanger) {
        this.zanger = zanger;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    
    

}
