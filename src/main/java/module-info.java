module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.slf4j;


    opens pl.xenox to javafx.fxml;
    exports pl.xenox;
    exports pl.xenox.algorithm;
    opens pl.xenox.algorithm to javafx.fxml;
    exports pl.xenox.gui;
    opens pl.xenox.gui to javafx.fxml;
}