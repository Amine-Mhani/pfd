package ma.ensaj.dem1.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CarAccelerometer extends Application {

    private double pitch = 0;
    private double roll = 0;
    private double heading = 0;

    private Line horizonLine;
    private Line pitchIndicator;
    private Line rollIndicatorLeft;
    private Line rollIndicatorRight;
    private Line headingIndicator;
    private Circle aircraftSymbol;
    private Text headingText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

        // Create the horizon line
        horizonLine = new Line(-300, 0, 300, 0);
        horizonLine.setStroke(Color.WHITE);
        horizonLine.setStrokeWidth(2);
        root.getChildren().add(horizonLine);

        // Create the pitch indicator
        pitchIndicator = new Line(0, 0, 0, -200);
        pitchIndicator.setStroke(Color.WHITE);
        pitchIndicator.setStrokeWidth(2);
        root.getChildren().add(pitchIndicator);

        // Create the roll indicators
        rollIndicatorLeft = new Line(-40, 0, -10, 0);
        rollIndicatorLeft.setStroke(Color.WHITE);
        rollIndicatorLeft.setStrokeWidth(2);
        root.getChildren().add(rollIndicatorLeft);

        rollIndicatorRight = new Line(10, 0, 40, 0);
        rollIndicatorRight.setStroke(Color.WHITE);
        rollIndicatorRight.setStrokeWidth(2);
        root.getChildren().add(rollIndicatorRight);

        // Create the heading indicator
        headingIndicator = new Line(0, 0, 0, 150);
        headingIndicator.setStroke(Color.WHITE);
        headingIndicator.setStrokeWidth(2);
        root.getChildren().add(headingIndicator);

        // Create the aircraft symbol
        aircraftSymbol = new Circle(0, 0, 30);
        aircraftSymbol.setFill(Color.TRANSPARENT);
        aircraftSymbol.setStroke(Color.WHITE);
        aircraftSymbol.setStrokeWidth(2);
        root.getChildren().add(aircraftSymbol);

        // Create the heading text
        headingText = new Text("0");
        headingText.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headingText.setFill(Color.WHITE);
        headingText.setX(-50);
        headingText.setY(200);
        root.getChildren().add(headingText);


        Scene scene = new Scene(root, 600, 400, Color.BLACK);

        primaryStage.setTitle("Airplane EWD");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}