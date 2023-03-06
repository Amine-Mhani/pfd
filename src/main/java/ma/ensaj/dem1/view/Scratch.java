package ma.ensaj.dem1.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Scratch extends Application {

    int j;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a new Arc object
        VBox scale = new VBox();

        Arc arc = new Arc();

        Rectangle rec = new Rectangle(80, 40);

        rec.setFill(Color.TRANSPARENT);
        rec.setStroke(Color.WHITE);
        rec.setTranslateX(140);



        // Set the properties of the arc
        arc.setCenterX(400);
        arc.setCenterY(300);
        arc.setRadiusX(180);
        arc.setRadiusY(180);
        arc.setStartAngle(0);
        arc.setLength(180);
        arc.setType(ArcType.OPEN);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.WHITE);

        double startX = 450;
        double startY = 0;
        double endX = 270;
        double endY = 0;



        Line hourHand = new Line(startX, startY, endX, endY);
        hourHand.setStrokeWidth(3);
        hourHand.setStroke(Color.GREEN);
        hourHand.setStrokeLineCap(StrokeLineCap.ROUND);

        Circle round = new Circle();
        round.setCenterX(startX);
        round.setCenterY(startY);
        round.setStroke(Color.GREEN);
        round.setStrokeWidth(3);
        round.setRadius(8);



        Line hourHand2 = new Line(startX, startY, 625, endY);
        hourHand2.setStrokeWidth(5);
        hourHand2.setStroke(Color.TRANSPARENT);
        hourHand2.setStrokeLineCap(StrokeLineCap.ROUND);

        Group neele = new Group();
        neele.setTranslateY(-10);
        neele.getChildren().addAll(hourHand, round, hourHand2);

        neele.setStyle("-fx-border-color: red; -fx-border-width: 1");





        scale.getChildren().addAll(arc, neele, rec);

        // Add the arc to a Pane




        // Create a Scene and add the Pane to it
        Scene scene = new Scene(scale, 800, 600);
        scene.setFill(Color.BLACK);


        // Set the title and show the stage
        primaryStage.setTitle("Half Circle Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
