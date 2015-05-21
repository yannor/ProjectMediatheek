package gui;

import java.io.*;
import java.util.logging.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javax.swing.*;

import domein.*;

public class Login extends GridPane
{
    ScreenSwitcher switcher;

    @FXML
    private TextField txtGebruikersnaam;

    @FXML
    private PasswordField txtWachtwoord;

    @FXML
    private Button btnLogin;

    public Login(ScreenSwitcher switcher)
    {
        this.switcher = switcher;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        try
        {
            loader.setRoot(this);
            loader.setController(this);
            loader.load();

        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void login(ActionEvent event)
    {
        // moet uit de db komen (zoeken op login/gebruikersnaam)
        Gebruiker admin = new Gebruiker();
        admin.setTypeGebruiker(TypeGebruiker.ADMIN);
        admin.getTypeGebruiker().setWachtwoord("admin");
        Gebruiker medewerker = new Gebruiker();
        medewerker.setTypeGebruiker(TypeGebruiker.ANDERE);
        medewerker.getTypeGebruiker().setWachtwoord("medewerker");
        

        if (admin.getTypeGebruiker().getEncryptedWachtwoord().equals(TypeGebruiker.encrypteerWachtwoord(txtWachtwoord.getText())))
        {
            switcher.setGebruiker(admin);
            switcher.homePageIn();
            switcher.setTop(null);
        }
        else if (medewerker.getTypeGebruiker().getEncryptedWachtwoord().equals(TypeGebruiker.encrypteerWachtwoord(txtWachtwoord.getText())))
        {
            switcher.setGebruiker(medewerker);
            switcher.homePageIn();
            switcher.setTop(null);
        }
        else
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                JOptionPane.showMessageDialog(null, "Fout wachtwoord", "Inane error", JOptionPane.ERROR_MESSAGE);
                txtWachtwoord.setText("");
            }
        }
    }

    @FXML
    public void handleLoginEnter(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            txtWachtwoord.requestFocus();
        }
    }

    @FXML
    public void handlePasswordEnter(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            btnLogin.fire();
        }
    }
}
