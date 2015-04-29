
package items;
/**
 *
 * @author Yannick
 */
public enum Beschikbaarheid {

    BESCHIKBAAR("Beschikbaar"),
    NIET_BESCHIKBAAR("Niet beschikbaar");

    
    private final String beschikbaar;

    Beschikbaarheid(String beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    @Override
    public String toString() {
        return beschikbaar;
    }
}

