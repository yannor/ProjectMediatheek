package gui;

import javafx.scene.layout.BorderPane;

public class ScreenSwitcher extends BorderPane {

    private HomePage in;
    private ColorBar colorBar;
    private Zoeken zoeken;
    private DetailPage details;
    private UitleningPage uitleningen;
    
    private BoekenPage boeken;

    public ScreenSwitcher() {
        initialize();
        setMinSize(1200, 800);
        setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
       
    }

    private void initialize() {
        colorBar = new ColorBar(this);
        //colorBar.setController(new ColorBar());
        this.uitleningen= new UitleningPage(this);
        this.details = new DetailPage(this);
        this.in = new HomePage(this);
        this.zoeken = new Zoeken(this);
        
        
        this.boeken = new BoekenPage(this);


    }


    public void homePageIn() {
        
        setTop(null);
        setCenter(in);
        setLeft(null);
    }

    public void zoeken(boolean aangemeld) {
       
        setTop(new ColorBar(this,aangemeld));
        setLeft(zoeken);
        setCenter(details);
    }
    
     public void uitleningen() {
        //colorBar.setTitel("Homepage in");
        setTop(new ColorBar(this));
        setCenter(uitleningen);
    }


}
