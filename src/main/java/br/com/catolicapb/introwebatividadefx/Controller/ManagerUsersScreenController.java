package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Dao.UserDao;
import br.com.catolicapb.introwebatividadefx.Interface.IOnChangeScreen;
import br.com.catolicapb.introwebatividadefx.Model.User;
import br.com.catolicapb.introwebatividadefx.Service.AuthService;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.List;

public class ManagerUsersScreenController implements IOnChangeScreen {

    @FXML
    private TableView<User> tableUsuarios;

    @FXML
    private TableColumn<User, String> colLogin;

    @FXML
    private TableColumn<User, String> colTipoUser;

    @FXML
    private TableColumn<User, Void> colEditar;

    @FXML
    void btBackAction () {
        AppController.changeScreen("main");
    }

    @FXML
    protected void initialize() {
        ScreenManager.addOnChangeScreenListener(this);
        configurarColunas();
    }

    @Override
    public void onScreenChanged(String newScreen, String userID, Object data) {
        if (newScreen.equals("managerUsers")) {
            carregarUsuarios();
        }
    }

    private void configurarColunas() {
        colLogin.setCellValueFactory(new PropertyValueFactory<>("loginuser"));
        colTipoUser.setCellValueFactory(new PropertyValueFactory<>("tipouser"));

        colEditar.setCellFactory(new Callback<>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button();

                    {
                        FontAwesomeIconView editarIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
                        editarIcon.getStyleClass().add("icon-blue");

                        btn.setGraphic(editarIcon);

                        btn.getStyleClass().addAll("action-button");

                        btn.setOnAction(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            editarUsuario(user);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox buttonBox = new HBox(10, btn);
                            buttonBox.setAlignment(Pos.CENTER);
                            setGraphic(buttonBox);
                        }
                    }
                };
            }
        });
    }

    private void carregarUsuarios() {
        try {
            String token = AuthService.getAccessToken();
            List<User> usuarios = UserDao.listarUsuarios(token);
            ObservableList<User> usuariosObservable = FXCollections.observableArrayList(usuarios);
            tableUsuarios.setItems(usuariosObservable);
        } catch (Exception e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    private void editarUsuario(User user) {
        AppController.changeScreen("editUser", null, user);
    }
}
