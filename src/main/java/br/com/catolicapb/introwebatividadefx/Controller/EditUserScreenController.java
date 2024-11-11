package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Dao.UserDao;
import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.User;
import br.com.catolicapb.introwebatividadefx.Service.AuthService;
import br.com.catolicapb.introwebatividadefx.Util.AlertUtils;
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
    void btUpdateUserAction() throws Exception {
        String newType = cbUserType.getValue();
        if (newType != null && user != null) {
            user.setTipouser(newType);
            UserDao.atualizarUsuario(AuthService.getAccessToken(), user);
            AlertUtils.showInfo("Sucesso", "O tipo do usuário \"" + user.getLoginuser().toUpperCase() + "\" foi atualizado para \"" + newType.toUpperCase() + "\" com sucesso!");
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
