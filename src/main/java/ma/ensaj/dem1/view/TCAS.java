package ma.ensaj.dem1.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class TCAS extends Application {


    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.BLACK);


        // Draw the map
        Arc arc = new Arc();
        Arc arc1 = new Arc();
        Arc arc2 = new Arc();


        arc.setCenterX(400);
        arc.setCenterY(300);
        arc.setRadiusX(160);
        arc.setRadiusY(150);
        arc.setStartAngle(30);
        arc.setLength(120);
        arc.setType(ArcType.OPEN);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.WHITE);

        arc1.setCenterX(400);
        arc1.setCenterY(300);
        arc1.setRadiusX(110);
        arc1.setRadiusY(110);
        arc1.setStartAngle(40);
        arc1.setLength(100);
        arc1.setType(ArcType.OPEN);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setStroke(Color.WHITE);

        arc2.setCenterX(400);
        arc2.setCenterY(300);
        arc2.setRadiusX(140);
        arc2.setRadiusY(140);
        arc2.setStartAngle(30);
        arc2.setLength(120);
        arc2.setType(ArcType.OPEN);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setStroke(Color.WHITE);

        Circle ownAircraft = new Circle(400, 300, 5, Color.WHITE);

        root.getChildren().addAll(arc, arc1, ownAircraft);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public double calculateDistance(Circle c1, Circle c2) {
        double dx = c1.getCenterX() - c2.getCenterX();
        double dy = c1.getCenterY() - c2.getCenterY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
