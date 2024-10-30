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

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent fxmlLogin = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/LoginScreen.fxml"));
        loginScene = new DraggableScene(fxmlLogin);
        Parent fxmlRegister = FXMLLoader.load(AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/RegisterScreen.fxml"));
        registerScene = new DraggableScene(fxmlRegister);
        Parent fxmlMain = FXMLLoader.load((AppController.class.getResource("/br/com/catolicapb/introwebatividadefx/MainScreen.fxml")));
        mainScene = new DraggableScene(fxmlMain);
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
        }
    }

    public static void changeScreen(String screen) {
        changeScreen(screen, null);
    }

    public static void main(String[] args) {
        launch();
    }
}
