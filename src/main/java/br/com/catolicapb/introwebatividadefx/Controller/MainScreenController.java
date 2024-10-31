package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Dao.ProductDao;
import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.Product;
import br.com.catolicapb.introwebatividadefx.Service.AuthService;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;


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
            try {
                String token = AuthService.getAccessToken();

            } catch (Exception e) {
                e.printStackTrace();
            }
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
