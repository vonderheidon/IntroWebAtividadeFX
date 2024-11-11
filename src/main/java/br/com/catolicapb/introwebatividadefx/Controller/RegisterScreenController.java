package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Dao.UserDao;
import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.User;
import br.com.catolicapb.introwebatividadefx.Service.AuthService;
import br.com.catolicapb.introwebatividadefx.Util.AlertUtils;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegisterScreenController implements IOnChangeScreen {

    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfPasswordConfirm;

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);
    }

    @Override
    public void onScreenChanged(String newScreen, String userID, Object data) {
        if (newScreen.equals("register")) {
            clearFields();
        }
    }

    @FXML
    void btCancelAction() {
        AppController.changeScreen("login");
    }

    @FXML
    void btRegisterAction() {
        registerProcedure();
    }

    private void registerProcedure() {
        User user = new User();
        String loginuser = tfLogin.getText();
        user.setLoginuser(loginuser);
        String password = pfPasswordConfirm.getText();
        user.setSenha(password);
        try {
            UserDao.cadastrarUsuario(AuthService.getAccessToken(), user);
            clearFields();
            AlertUtils.showInfo("Sucesso", "Usuário cadastrado com sucesso!");
            AppController.changeScreen("login");
        } catch (Exception ex) {
            AlertUtils.showWarning(null, ex.getMessage());
        }
    }

    private void clearFields() {
        tfLogin.clear();
        pfPassword.clear();
        pfPasswordConfirm.clear();
    }
}
