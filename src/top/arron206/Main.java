package top.arron206;

import top.arron206.model.Member;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("保龄球计分系统");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        Application.launch(args);
    }
}
