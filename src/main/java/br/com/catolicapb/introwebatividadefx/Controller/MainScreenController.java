package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Dao.UserDao;
import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.User;
import br.com.catolicapb.introwebatividadefx.Service.AuthService;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


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

    private void teste() {
        String token = AuthService.getAccessToken();
        UserDao userDao = new UserDao();
        User loggedInUser = userDao.getUserById(token);

        if (loggedInUser != null) {
            System.out.println("Usuário logado: " + loggedInUser.getUsername());
        } else {
            System.out.println("null");
        }
    }
}
