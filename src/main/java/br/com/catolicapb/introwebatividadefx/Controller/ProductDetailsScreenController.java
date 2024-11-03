package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.Product;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

public class ProductDetailsScreenController implements IOnChangeScreen {

    @FXML
    private TableView<Pair<String, String>> detailsTable;

    @FXML
    private TableColumn<Pair<String, String>, String> campoColumn;

    @FXML
    private TableColumn<Pair<String, String>, String> detalheColumn;

    private Product product;

    @FXML
    void btBackAction () {
        AppController.changeScreen("main");
    }

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);

        campoColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        detalheColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    @Override
    public void onScreenChanged(String newScreen, String userID, Object data) {
        if (newScreen.equals("productDetails") && data instanceof Product) {
            this.product = (Product) data;
            populateTable();
        }
    }

    private void populateTable() {
        ObservableList<Pair<String, String>> productDetails = FXCollections.observableArrayList(
                new Pair<>("ID", product.getId()),
                new Pair<>("Nome", product.getNome()),
                new Pair<>("Cadastrado por", product.getLoginuser()),
                new Pair<>("Quantidade", String.valueOf(product.getQtde())),
                new Pair<>("Preço", String.valueOf(product.getPreco()))
        );
        detailsTable.setItems(productDetails);
    }
}
