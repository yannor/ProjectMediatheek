
package items;

import db.DbConnect;
import java.util.List;

public class Cd extends Item {

    private List<Lied> liedjes;
    private DbConnect connect= new DbConnect();

    public Cd(int id, String naam, String thema, String leeftijd, int aantal)
    {
        super(id, naam, thema, leeftijd, aantal);
    }

    public Cd(int id, String naam, String thema, String leeftijd, int aantal, List<Lied> liedjes) {
        super(id, naam, thema, leeftijd, aantal);
        this.liedjes=liedjes;   
        
    }

    public List<Lied> getLiedjes() {
        return liedjes;
    }

    public void setLiedjes(List<Lied> liedjes) {
        this.liedjes = liedjes;
    }
    
    public void vulCd(int cdId)
    {
        for(int i=0;i<connect.getLiedjes(cdId).size();i++)
        {
            liedjes.add(connect.getLiedjes(cdId).get(i));
        }
        
    }
    

}
