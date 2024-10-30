module br.com.catolicapb.introwebatividadefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;

    exports br.com.catolicapb.introwebatividadefx.Controller;
    opens br.com.catolicapb.introwebatividadefx.Controller to javafx.fxml;
}