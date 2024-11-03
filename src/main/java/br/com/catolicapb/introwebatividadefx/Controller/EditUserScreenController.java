package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.User;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class EditUserScreenController implements IOnChangeScreen {

    @FXML
    private Label lbUserName;

    @FXML
    private ComboBox<String> cbUserType;

    private User user;

    @FXML
    void btBackAction () {
        AppController.changeScreen("managerUsers");
    }

    @FXML
    void btUpdateUserAction() {
        String newType = cbUserType.getValue();
        if (newType != null && user != null) {
            user.setTipouser(newType);
        }
    }

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);
        cbUserType.getItems().addAll("normal", "super");
    }

    @Override
    public void onScreenChanged(String newScreen, String userID, Object data) {
        if (newScreen.equals("editUser") && data instanceof User) {
            this.user = (User) data;
            lbUserName.setText("Editar usuário " + user.getLoginuser());
            cbUserType.setValue(user.getTipouser());
        }
    }
}
