package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import javafx.fxml.FXML;

public class ManagerUsersScreenController implements IOnChangeScreen {
    @FXML
    void btBackAction () {
        AppController.changeScreen("main");
    }
    @Override
    public void onScreenChanged(String newScreen, String userID) {

    }
}
