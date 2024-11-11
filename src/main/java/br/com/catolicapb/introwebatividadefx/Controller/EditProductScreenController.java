package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.Product;
import br.com.catolicapb.introwebatividadefx.Dao.ProductDao;
import br.com.catolicapb.introwebatividadefx.Service.AuthService;
import br.com.catolicapb.introwebatividadefx.Util.AlertUtils;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class EditProductScreenController implements IOnChangeScreen {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfQtde;

    private Product product;

    @FXML
    void btBackAction () {
        AppController.changeScreen("main");
    }

    @FXML
    void btSaveAction () {
        try {
            product.setNome(tfNome.getText());
            product.setPreco(Double.parseDouble(tfPreco.getText()));
            product.setQtde(Integer.parseInt(tfQtde.getText()));

            ProductDao.atualizarProduto(AuthService.getAccessToken(), product);

            AlertUtils.showInfo("Sucesso", "Produto atualizado com sucesso!");

            AppController.changeScreen("main");

        } catch (Exception e) {
            e.printStackTrace();
            AlertUtils.showError("Erro", "Falha ao atualizar o produto: " + e.getMessage());
        }
    }

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);
    }

    @Override
    public void onScreenChanged(String newScreen, String userID, Object data) {
        clearFields();
        if ("editProduct".equals(newScreen) && data instanceof Product) {
            this.product = (Product) data;
            populateFields();
        }
    }

    private void populateFields() {
        if (product != null) {
            tfNome.setText(product.getNome());
            tfQtde.setText(String.valueOf(product.getQtde()));
            tfPreco.setText(String.valueOf(product.getPreco()));
        }
    }

    private void clearFields() {
        tfNome.clear();
        tfPreco.clear();
        tfQtde.clear();
    }
}
