package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegisterScreenController implements IOnChangeScreen {

    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfPasswordConfirm;

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);
    }

    @Override
    public void onScreenChanged(String newScreen, String userID) {
        if (newScreen.equals("register")) {
            clearFields();
        }
    }

    @FXML
    void btCancelAction() {
        AppController.changeScreen("login");
    }

    @FXML
    void btRegisterAction() {
        registerProcedure();
    }

    private void registerProcedure() {

    }

    private void clearFields() {
        tfLogin.setText("");
        pfPassword.setText("");
        pfPasswordConfirm.setText("");
    }
}
