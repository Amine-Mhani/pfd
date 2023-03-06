package ma.ensaj.dem1.view;

import java.text.SimpleDateFormat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class ND extends Application {
    int counter = 1;
    int distance =10000;
    int helper = 0;
    int helper2= 0;

    @Override
    public void start(Stage primaryStage) {

        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
        final NumberAxis yAxis = new NumberAxis();


//creating the line chart with two axis created above
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setAnimated(false); // disable animations
        lineChart.setCreateSymbols(false);
        lineChart.setHorizontalGridLinesVisible(false);
        lineChart.setVerticalGridLinesVisible(true);
        lineChart.setVerticalZeroLineVisible(true);

        lineChart.lookup(".chart-plot-background").setStyle("-fx-background:black;");
        lineChart.setPrefSize(400, 400);
//defining series to display data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        Platform.runLater(() ->
                series1.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: yellow;")
        );

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        Platform.runLater(() ->
                series2.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: purple;")
        );

// add series to chart
        lineChart.getData().addAll(series1, series2);

// setup a scheduled executor to periodically put data into the chart
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

// put dummy data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            int a = 1000;

            // Update the chart
            Platform.runLater(() -> {
                // get current time
                int x = counter*distance;
                counter++;
                // put random numbers with current time for both series
                series1.getData().add(new XYChart.Data<>(String.valueOf(x)+" m", a*helper));
                series2.getData().add(new XYChart.Data<>(String.valueOf(x)+" m", a*helper2));

                if (series1.getData().size() > 10) {
                    series1.getData().remove(0);
                    series2.getData().remove(0);
                }
            });
        }, 0, 1, TimeUnit.SECONDS);


        lineChart.getXAxis().setSide(Side.TOP);
// disable default symbols
        lineChart.setCreateSymbols(false);
        lineChart.setStyle("-fx-background-color: black");

// Create a scene with the line chart
        Scene scene = new Scene(new StackPane(lineChart), 800, 300);
        scene.setFill(Color.BLACK);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if(helper!=11)
                        helper++;
                    break;
                case DOWN:
                    if(helper!=0)
                        helper--;
                    break;
                case LEFT:
                    // handle arrow left key
                    break;
                case RIGHT:
                    // handle arrow right key
                    break;
                case Z:
                    if(helper2!=11)
                        helper2++;
                    break;
                case S:
                    if(helper2!=0)
                        helper2--;
                    break;
                default:
                    break;
            }
        });
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}