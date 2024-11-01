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
    private static Scene userDetailsScene;
    private static Scene addProductScene;
    private static Scene editProductScene;
    private static Scene editUserScene;

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
        Parent fxmlUserDetails = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/UserDetailsScreen.fxml"));
        userDetailsScene = new DraggableScene(fxmlUserDetails);
        Parent fxmlEditUser = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/EditUserScreen.fxml"));
        editUserScene = new DraggableScene(fxmlEditUser);
        Parent fxmlEditProduct = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/EditProductScreen.fxml"));
        editProductScene = new DraggableScene(fxmlEditProduct);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void changeScreen(String screen, String userID) {
        switch (screen) {
            case "login":
                stage.setScene(loginScene);
                ScreenManager.notifyAllListeners("login", userID);
                break;
            case "register":
                stage.setScene(registerScene);
                ScreenManager.notifyAllListeners("register", userID);
                break;
            case "main":
                stage.setScene(mainScene);
                ScreenManager.notifyAllListeners("main", userID);
                break;
            case "addProduct":
                stage.setScene(addProductScene);
                ScreenManager.notifyAllListeners("addProduct", userID);
                break;
            case "productDetails":
                stage.setScene(productDetailsScene);
                ScreenManager.notifyAllListeners("productDetails", userID);
                break;
            case "userDetails":
                stage.setScene(userDetailsScene);
                ScreenManager.notifyAllListeners("userDetails", userID);
                break;
            case "managerUsers":
                stage.setScene(managerUsersScene);
                ScreenManager.notifyAllListeners("managerUsers", userID);
                break;
            case "editUser":
                stage.setScene(editUserScene);
                ScreenManager.notifyAllListeners("editUser", userID);
                break;
            case "editProduct":
                stage.setScene(editProductScene);
                ScreenManager.notifyAllListeners("editProduct", userID);
                break;
        }
    }

    public static void changeScreen(String screen) {
        changeScreen(screen, null);
    }

    public static void main(String[] args) {
        launch();
    }
}
