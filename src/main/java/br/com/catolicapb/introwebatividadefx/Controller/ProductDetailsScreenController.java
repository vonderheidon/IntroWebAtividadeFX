package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.Product;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductDetailsScreenController implements IOnChangeScreen {

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
        if (newScreen.equals("productDetails")) {
            System.out.println(product.getNome());
        }
    }

    public static void SetProduct(Product product) {
        ProductDetailsScreenController.product = product;
    }
}
