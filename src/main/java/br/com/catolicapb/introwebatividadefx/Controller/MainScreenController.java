package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Dao.ProductDao;
import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.Product;
import br.com.catolicapb.introwebatividadefx.Model.User;
import br.com.catolicapb.introwebatividadefx.Service.AuthService;
import br.com.catolicapb.introwebatividadefx.Util.AlertUtils;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.util.List;

public class MainScreenController implements IOnChangeScreen {

    @FXML
    private TableView<Product> productTable;
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
    private String userID;
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private FilteredList<Product> filteredProducts;
    private String token;
    private int rowsPerPage = 5;
    private int currentPage = 1;

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);

        filteredProducts = new FilteredList<>(FXCollections.observableArrayList());

        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("qtde"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        addActionsColumn();
        setupSearch();
        updatePage();
    }

    private void addActionsColumn() {
        acoesColumn.setCellFactory(col -> new TableCell<>() {
            private final Button detalhesBtn = new Button();
            private final Button editarBtn = new Button();
            private final Button excluirBtn = new Button();

            {
                FontAwesomeIconView detalhesIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                detalhesIcon.getStyleClass().add("icon-blue");

                FontAwesomeIconView editarIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                editarIcon.getStyleClass().add("icon-blue");

                FontAwesomeIconView excluirIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                excluirIcon.getStyleClass().add("icon-red");

                detalhesBtn.setGraphic(detalhesIcon);
                editarBtn.setGraphic(editarIcon);
                excluirBtn.setGraphic(excluirIcon);

                detalhesBtn.getStyleClass().addAll("action-button");
                editarBtn.getStyleClass().addAll("action-button");
                excluirBtn.getStyleClass().addAll("action-button");

                detalhesBtn.setOnAction(e -> {
                    try {
                        verDetalhes(getTableView().getItems().get(getIndex()).getId());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                editarBtn.setOnAction(e -> editarProduto(getTableView().getItems().get(getIndex())));
                excluirBtn.setOnAction(e -> excluirProduto(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttonBox = new HBox(10, detalhesBtn, editarBtn, excluirBtn);
                    buttonBox.setAlignment(Pos.CENTER);
                    setGraphic(buttonBox);
                }
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
        currentPage = Math.max(1, Math.min(currentPage, totalPages));

        int startRow = (currentPage - 1) * rowsPerPage;
        int endRow = Math.min(startRow + rowsPerPage, totalRows);

        productTable.setItems(FXCollections.observableArrayList(filteredProducts.subList(startRow, endRow)));
        pageDisplay.setText("Página " + currentPage + " de " + totalPages);
        prevPageBtn.setDisable(currentPage == 1);
        nextPageBtn.setDisable(currentPage == totalPages);

        productTable.refresh();
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
    public void onScreenChanged(String newScreen, String userID, Object data) {
        if (newScreen.equals("main")) {
            try {
                token = AuthService.getAccessToken();
                this.userID = userID;

                User user = AuthService.getUser();
                if (user != null && user.getTipouser().equals("super")) {
                    btUserManagement.setVisible(true);
                } else {
                    btUserManagement.setVisible(false);
                }

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
        AppController.changeScreen("addProduct", userID, null);
    }

    @FXML
    void btLogoutAction() {
        AuthService.logout();
        AppController.changeScreen("login");
    }

    @FXML
    void btUserManagementAction() {
        AppController.changeScreen("managerUsers");
    }

    private void verDetalhes(String id) throws Exception {
        Product product = ProductDao.buscarProdutoPorId(AuthService.getAccessToken(), id);
        if (product != null) {
            AppController.changeScreen("productDetails", null, product);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void editarProduto(Product product) {
        AppController.changeScreen("editProduct", null, product);
    }

    private void excluirProduto(Product product) {
        boolean confirmed = AlertUtils.showConfirmation("Excluir Produto", "Deseja realmente excluir este produto?");
        if (confirmed) {
            try {
                ProductDao.excluirProduto(AuthService.getAccessToken(), product.getId());
                allProducts.remove(product);
                updatePage();
            } catch (Exception e) {
                AlertUtils.showError("Erro ao Excluir Produto", "Ocorreu um erro ao tentar excluir o produto.");
                e.printStackTrace();
            }
        }
    }

}
