package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Dao.ProductDao;
import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.Product;
import br.com.catolicapb.introwebatividadefx.Service.AuthService;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.util.List;

public class MainScreenController implements IOnChangeScreen {

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nomeColumn;
    @FXML
    private TableColumn<Product, Integer> quantidadeColumn;
    @FXML
    private TableColumn<Product, Double> precoColumn;
    @FXML
    private TableColumn<Product, Void> acoesColumn;
    @FXML
    private Button prevPageBtn;
    @FXML
    private Button nextPageBtn;
    @FXML
    private Label pageDisplay;
    @FXML
    private TextField searchBar;
    @FXML
    private Button btUserManagement;
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private FilteredList<Product> filteredProducts;
    private String token;
    private int rowsPerPage = 5;
    private int currentPage = 1;

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);

        filteredProducts = new FilteredList<>(FXCollections.observableArrayList());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("qtde"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        addActionsColumn();
        setupSearch();
        updatePage();
    }

    private void addActionsColumn() {
        acoesColumn.setCellFactory(col -> new TableCell<>() {
            private final Button detalhesBtn = new Button("Detalhes");
            private final Button editarBtn = new Button("Editar");
            private final Button excluirBtn = new Button("Excluir");

            {
                detalhesBtn.setOnAction(e -> verDetalhes(getTableView().getItems().get(getIndex())));
                editarBtn.setOnAction(e -> editarProduto(getTableView().getItems().get(getIndex())));
                excluirBtn.setOnAction(e -> excluirProduto(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) setGraphic(null);
                else setGraphic(new HBox(detalhesBtn, editarBtn, excluirBtn));
            }
        });
    }

    private void setupSearch() {
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProducts.setPredicate(product -> product.getNome().toLowerCase().contains(newValue.toLowerCase()));
            currentPage = 1;
            updatePage();
        });
    }

    private void updatePage() {
        int totalRows = filteredProducts.size();
        int totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
        int startRow = (currentPage - 1) * rowsPerPage;
        int endRow = Math.min(startRow + rowsPerPage, totalRows);

        productTable.setItems(FXCollections.observableArrayList(filteredProducts.subList(startRow, endRow)));
        pageDisplay.setText("Página " + currentPage + " de " + totalPages);
        prevPageBtn.setDisable(currentPage == 1);
        nextPageBtn.setDisable(currentPage == totalPages);
    }

    @FXML
    private void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            updatePage();
        }
    }

    @FXML
    private void nextPage() {
        if (currentPage < Math.ceil((double) filteredProducts.size() / rowsPerPage)) {
            currentPage++;
            updatePage();
        }
    }

    @Override
    public void onScreenChanged(String newScreen, String userID) {
        if (newScreen.equals("main")) {
            try {
                token = AuthService.getAccessToken();

                List<Product> produtos = ProductDao.listarProdutos(token);
                allProducts.setAll(produtos);

                filteredProducts = new FilteredList<>(allProducts);
                productTable.setItems(filteredProducts);
                updatePage();

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

    private void verDetalhes(Product product) {

    }

    private void editarProduto(Product product) {

    }

    private void excluirProduto(Product product) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Deseja realmente excluir?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                allProducts.remove(product);
                updatePage();
            }
        });
    }
}
