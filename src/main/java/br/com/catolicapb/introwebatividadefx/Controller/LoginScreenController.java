package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginScreenController implements IOnChangeScreen {

    @FXML
    private PasswordField pfPassword;
    @FXML
    private TextField tfLogin;

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);
    }

    @Override
    public void onScreenChanged(String newScreen, String userID) {
        if (newScreen.equals("login")) {
            clearFields();
        }
    }

    @FXML
    void btEnterAction() {
        loginProcedure();
    }

    @FXML
    void btRegisterAction() {
        AppController.changeScreen("register");
    }

    private void loginProcedure() {
        AppController.changeScreen("main");
    }

    private void clearFields() {
        tfLogin.setText("");
        pfPassword.setText("");
    }
}
