module br.com.catolicapb.introwebatividadefx {
    requires javafx.controls;
    requires javafx.fxml;

    exports br.com.catolicapb.introwebatividadefx.Controller;
    opens br.com.catolicapb.introwebatividadefx.Controller to javafx.fxml;
}