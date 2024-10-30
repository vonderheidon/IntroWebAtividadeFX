package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MainScreenController implements IOnChangeScreen {

    @FXML
    private Button btUserManagement;

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);
    }

    @Override
    public void onScreenChanged(String newScreen, String userID) {
        if (newScreen.equals("main")) {

        }
    }

    @FXML
    void btAddNewProductAction() {

    }

    @FXML
    void btLogoutAction() {

    }

    @FXML
    void btUserManagementAction() {

    }
}
