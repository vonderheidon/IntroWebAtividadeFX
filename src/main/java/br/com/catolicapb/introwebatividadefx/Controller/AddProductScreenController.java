package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddProductScreenController implements IOnChangeScreen {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfQtde;

    @FXML
    void btAddAction() {

    }

    @FXML
    void btBackAction () {
        AppController.changeScreen("main");
    }

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);
    }

    @Override
    public void onScreenChanged(String newScreen, String userID) {

    }
}
