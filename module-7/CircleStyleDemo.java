//Kristopher Kuenning
//09/21/2025
//CSD420
//Module 7 Programming Assignment

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleStyleDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create circles
        Circle circle1 = new Circle(50);
        circle1.getStyleClass().add("plaincircle"); // Use class

        Circle circle2 = new Circle(50);
        circle2.setId("redcircle"); // Use ID

        Circle circle3 = new Circle(50);
        circle3.setId("greencircle"); // Use ID

        Circle circle4 = new Circle(50);
        circle4.getStyleClass().add("plaincircle"); // Use class again

        // Arrange circles horizontally
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(circle1, circle2, circle3, circle4);

        // Create scene and link CSS
        Scene scene = new Scene(hbox, 400, 150, Color.LIGHTGRAY);
        scene.getStylesheets().add("mystyle.css");

        primaryStage.setTitle("Circle Style Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
