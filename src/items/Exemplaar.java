
package items;

public class Exemplaar {

private Item item;
private String code;
private boolean beschikbaar;

    public Exemplaar(String code,Item item,  boolean beschikbaar) {
        this.item = item;
        this.code = code;
        this.beschikbaar = beschikbaar;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isBeschikbaar() {
        return beschikbaar;
    }

    public void setBeschikbaar(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    

}
