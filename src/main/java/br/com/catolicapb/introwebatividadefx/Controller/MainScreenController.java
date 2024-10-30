package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;


public class MainScreenController implements IOnChangeScreen {

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);
    }
    @Override
    public void onScreenChanged(String newScreen, String userID) {

    }
}
