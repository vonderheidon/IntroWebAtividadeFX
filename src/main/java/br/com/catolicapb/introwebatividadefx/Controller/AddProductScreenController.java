package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Dao.ProductDao;
import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.Product;
import br.com.catolicapb.introwebatividadefx.Service.AuthService;
import br.com.catolicapb.introwebatividadefx.Util.AlertUtils;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddProductScreenController implements IOnChangeScreen {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfQtde;

    private String userID;

    @FXML
    void btAddAction() {
        addProduct();
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
    public void onScreenChanged(String newScreen, String userID, Object data) {
        if (newScreen.equals("addProduct")) {
            this.userID = userID;
        }
    }

    private void addProduct() {
        try {
            Product product = new Product();
            product.setNome(tfNome.getText());
            product.setPreco(Double.parseDouble(tfPreco.getText()));
            product.setQtde(Integer.parseInt(tfQtde.getText()));
            product.setLoginuser(userID);

            ProductDao.inserirProduto(AuthService.getAccessToken(), product);

            clearFields();

            AlertUtils.showInfo("Sucesso", "Produto adicionado com sucesso!");

            AppController.changeScreen("main");
        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.showError("Erro", "Falha ao atualizar o produto: " + ex.getMessage());
        }

    }

    private void clearFields() {
        tfNome.clear();
        tfPreco.clear();
        tfQtde.clear();
    }
}
