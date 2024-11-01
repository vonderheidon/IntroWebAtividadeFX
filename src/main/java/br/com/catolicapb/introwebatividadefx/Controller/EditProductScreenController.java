package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.Product;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;

public class EditProductScreenController implements IOnChangeScreen {

    private static Product product;

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

    public static void setProduct(Product product) {
        EditProductScreenController.product = product;
    }
}
