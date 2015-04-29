
package domein;

public enum TypeGebruiker {


	ADMIN("Administrator"), 
        ANDERE("Vrijwilliger"), 
        LEERLING("Leerling");

	private final String gebruiker;

	private TypeGebruiker(String gebruiker) {
	    this.gebruiker = gebruiker;
	}

	@Override
	public String toString() {
	    return gebruiker;
	}
    }

