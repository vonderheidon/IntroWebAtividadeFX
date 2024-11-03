package br.com.catolicapb.introwebatividadefx.Controller;

import br.com.catolicapb.introwebatividadefx.Util.DraggableScene;
import br.com.catolicapb.introwebatividadefx.Util.ScreenManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController extends Application {

    private static Stage stage;
    private static Scene loginScene;
    private static Scene registerScene;
    private static Scene mainScene;
    private static Scene managerUsersScene;
    private static Scene productDetailsScene;
    private static Scene editUserScene;
    private static Scene addProductScene;
    private static Scene editProductScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        Parent fxmlLogin = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/LoginScreen.fxml"));
        loginScene = new DraggableScene(fxmlLogin);
        Parent fxmlRegister = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/RegisterScreen.fxml"));
        registerScene = new DraggableScene(fxmlRegister);
        Parent fxmlMain = FXMLLoader.load((AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/MainScreen.fxml")));
        mainScene = new DraggableScene(fxmlMain);
        Parent fxmlManagerUsers = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/ManagerUsersScreen.fxml"));
        managerUsersScene = new DraggableScene(fxmlManagerUsers);
        Parent fxmlProductDetail = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/ProductDetailsScreen.fxml"));
        productDetailsScene = new DraggableScene(fxmlProductDetail);
        Parent fxmlAddProduct = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/AddProductScreen.fxml"));
        addProductScene = new DraggableScene(fxmlAddProduct);
        Parent fxmlEditUser = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/EditUserScreen.fxml"));
        editUserScene = new DraggableScene(fxmlEditUser);
        Parent fxmlEditProduct = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/EditProductScreen.fxml"));
        editProductScene = new DraggableScene(fxmlEditProduct);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void changeScreen(String screen, String userID, Object data) {
        switch (screen) {
            case "login":
                stage.setScene(loginScene);
                ScreenManager.notifyAllListeners("login", userID, data);
                break;
            case "register":
                stage.setScene(registerScene);
                ScreenManager.notifyAllListeners("register", userID, data);
                break;
            case "main":
                stage.setScene(mainScene);
                ScreenManager.notifyAllListeners("main", userID, data);
                break;
            case "addProduct":
                stage.setScene(addProductScene);
                ScreenManager.notifyAllListeners("addProduct", userID, data);
                break;
            case "productDetails":
                stage.setScene(productDetailsScene);
                ScreenManager.notifyAllListeners("productDetails", userID, data);
                break;
            case "editUser":
                stage.setScene(editUserScene);
                ScreenManager.notifyAllListeners("editUser", userID, data);
                break;
            case "managerUsers":
                stage.setScene(managerUsersScene);
                ScreenManager.notifyAllListeners("managerUsers", userID, data);
                break;
            case "editProduct":
                stage.setScene(editProductScene);
                ScreenManager.notifyAllListeners("editProduct", userID, data);
                break;
        }
    }

    public static void changeScreen(String screen) {
        changeScreen(screen, null, null);
    }

    public static void main(String[] args) {
        launch();
    }
}
