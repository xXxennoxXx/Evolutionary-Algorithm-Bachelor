package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import static java.util.Objects.requireNonNull;

public class Run extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(requireNonNull(this.getClass().getResource("/Main.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(requireNonNull(this.getClass().getResource("/application.css")).toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
