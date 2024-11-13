module br.com.catolicapb.introwebatividadefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;
    requires de.jensd.fx.glyphs.fontawesome;

    exports br.com.catolicapb.introwebatividadefx.Controller;
    opens br.com.catolicapb.introwebatividadefx.Model to com.google.gson, javafx.base;
    opens br.com.catolicapb.introwebatividadefx.Controller to javafx.fxml;
}