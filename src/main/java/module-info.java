module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.slf4j;


    opens application to javafx.fxml;
    exports application;
    exports application.algorithm;
    opens application.algorithm to javafx.fxml;
}