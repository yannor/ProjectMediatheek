
package domein;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum TypeGebruiker {


	ADMIN("Administrator", "admin"), 
        ANDERE("Vrijwilliger","medewerker"), 
        LEERLING("Leerling", "");

	private final String gebruiker;
  
        private String wachtwoord;
        private String encryptedWachtwoord;
        

	private TypeGebruiker(String gebruiker, String wachtwoord) {
	    this.gebruiker = gebruiker;
            encryptedWachtwoord= encrypteerWachtwoord(wachtwoord);
            
	}

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }


 

	@Override
	public String toString() {
	    return gebruiker;
	}
        
        
         public static String encrypteerWachtwoord(String wachtwoord)
    {
        String eWachtwoord = "";
        try
        {
            BigInteger hash = new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(wachtwoord.getBytes("UTF-8")));
            eWachtwoord = hash.toString(16);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException ex)
        {
            Logger.getLogger(Gebruiker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eWachtwoord;
    }

    public String getEncryptedWachtwoord()
    {
        
        try
        {
            BigInteger hash = new BigInteger(1, MessageDigest.getInstance("SHA-256").digest(this.wachtwoord.getBytes("UTF-8")));
            encryptedWachtwoord = hash.toString(16);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException ex)
        {
            Logger.getLogger(Gebruiker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encryptedWachtwoord;
    }
    }

