package ma.ensaj.dem1.view;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.util.Duration;


import java.io.File;



public class v2 extends Application {

    private static final String OUTSIDE_TEXT = "Outside Label";
    double delta = 0.07;
    int j;

    Image plane1 = new Image("plane5.jpg");

    Image plane2 = new Image("plane3.png");
    ImageView pl = new ImageView(plane2);

    Image split = new Image("pfd4.jpg");
    Image indic = new Image("wing8.png");
    String filePath = "Airplane+8.mp3";
    Media media = new Media(new File(filePath).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);

    double distance = 0.0;

    private static final int WIDTH = 60;
    private static final int HEIGHT = 800;
    private static final int CENTER_X = WIDTH / 2;
    private static final int CENTER_Y = HEIGHT / 2;
    private static final int TICK_LENGTH = 10;
    private static final int TEXT_PADDING = 1;

    private static final int Translation = 350;


    private Robot robot = new Robot();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        final Label reporter = new Label(OUTSIDE_TEXT);
        final Label pfd = new Label();
        final Label scale = new Label();
        final Label scale2 = new Label();
        final Label monitored = new Label();





        monitored.setStyle("-fx-background-color: #87CEEB; -fx-text-fill: white; -fx-font-size: 20px; -fx-min-width: 1000;-fx-min-height: 300;-fx-cursor-size: 100px;");
        monitored.setCursor(new ImageCursor(plane1));

        monitored.setGraphic(pl);


        // Scale line
        Line scaleLine = new Line(CENTER_X, CENTER_Y, CENTER_X, CENTER_Y - 400);
        scaleLine.setStrokeWidth(3);
        scaleLine.setStroke(Color.BLACK);

        Line scaleLine2 = new Line(CENTER_X, CENTER_Y, CENTER_X, CENTER_Y - 400);
        scaleLine2.setStrokeWidth(3);
        scaleLine2.setStroke(Color.BLACK);

        // Needle
        Line needle = new Line(CENTER_X, 100, CENTER_X, 0);
        needle.setStroke(Color.RED);
        needle.setStrokeWidth(4);
        needle.setRotate(90);
        needle.setTranslateY(Translation);

        // Needle
        Line needle2 = new Line(CENTER_X, 100, CENTER_X, 0);
        needle2.setStroke(Color.RED);
        needle2.setStrokeWidth(4);
        needle2.setRotate(90);
        needle2.setTranslateY(Translation);



        // Tick marks and labels
        Pane tickPane = new Pane();
        for (int i = -400; i <= 0; i += 20) {
            Line tick = new Line(CENTER_X - TICK_LENGTH / 2, CENTER_Y + i, CENTER_X + TICK_LENGTH / 2, CENTER_Y + i);
            tickPane.getChildren().addAll(tick);


            if (i % 100 == 0) {
                Text label = new Text(Integer.toString(-i)+" m/s");
                label.setFont(Font.font(15));
                label.setX(CENTER_X + TICK_LENGTH + TEXT_PADDING);
                label.setY(CENTER_Y + i + TEXT_PADDING);
                tickPane.getChildren().add(label);
            }
        }

        Pane tickPane2 = new Pane();
        for (int i = -400; i <= 0; i += 20) {
            Line tick = new Line(CENTER_X - TICK_LENGTH / 2, CENTER_Y + i, CENTER_X + TICK_LENGTH / 2, CENTER_Y + i);
            tickPane2.getChildren().addAll(tick);


            if (i % 100 == 0) {
                Text label2 = new Text(Integer.toString(-i)+" m");
                label2.setFont(Font.font(15));
                label2.setX(CENTER_X + TICK_LENGTH + TEXT_PADDING);
                label2.setY(CENTER_Y + i + TEXT_PADDING);
                tickPane2.getChildren().add(label2);
            }
        }




        monitored.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String msg =
                        "(x: " + event.getX() + ", y: " + event.getY() + ") -- " +
                                "(sceneX: " + event.getSceneX() + ", sceneY: " + event.getSceneY() + ") -- " +
                                "(screenX: " + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";
                reporter.setText(msg);

                Point2D plTopLeftOnScreen = pl.localToScreen(0, 0);
                double dx = event.getScreenX() - plTopLeftOnScreen.getX();
                double dy = event.getScreenY() - plTopLeftOnScreen.getY();
                distance = Math.sqrt(dx * dx + dy * dy) - 218;



                System.out.println("Distance to pl: " + distance);
            }
        });
        monitored.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                reporter.setText(OUTSIDE_TEXT);
            }
        });

        scale.setGraphic(new Group(scaleLine, tickPane, needle));
        scale2.setGraphic(new Group(scaleLine2, tickPane2, needle2));

        StackPane stackPane = new StackPane();
        ScrollPane scrollPane = createScrollPane(createImageView(split));

        stackPane.getChildren().addAll(scrollPane, new ImageView(indic));
        pfd.setGraphic(stackPane);

        VBox leftBox = new VBox(10);
        leftBox.getChildren().setAll(monitored, reporter);
        leftBox.setStyle("-fx-background-color: black; -fx-padding: 5px; -fx-min-width: 400; -fx-max-height: 100");





        VBox middleBox = new VBox(1);
        middleBox.getChildren().setAll(scale);
        middleBox.setStyle("-fx-background-color: lightgray; -fx-padding: 5px; -fx-min-width: 100; -fx-max-height: 100; -fx-border-color: red; -fx-end-margin: 0");

        VBox endBox = new VBox(1);
        endBox.getChildren().setAll(scale2);
        endBox.setStyle("-fx-background-color: lightgray; -fx-padding: 5px; -fx-min-width: 100; -fx-max-height: 100");



        VBox rightBox = new VBox(1);
        rightBox.getChildren().setAll(pfd);
        rightBox.setStyle("-fx-background-color: black; -fx-end-margin: 0; -fx-border-color: red");
        rightBox.setPrefWidth(300);
        rightBox.setPrefHeight(60);

        HBox pfBox = new HBox(10);
        pfBox.setStyle("-fx-background-color: black; -fx-padding: 10px;");
        pfBox.getChildren().setAll(middleBox, rightBox, endBox);

        HBox pitBox = new HBox(10);
        pitBox.setStyle("-fx-background-color: black; -fx-padding: 10px;");
        pitBox.getChildren().setAll(pfBox);

        VBox root = new VBox(10);
        root.getChildren().setAll(pitBox, leftBox);

        root.setStyle("-fx-background-color: black; -fx-padding: 10px;");

        Scene scene = new Scene(root);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), needle);
        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), needle2);
        transition.setInterpolator(Interpolator.EASE_OUT);
        transition2.setInterpolator(Interpolator.EASE_OUT);

        scene.setOnKeyPressed(event -> {
            double vvalue = scrollPane.getVvalue();

            switch (event.getCode()) {
                case UP:
                    robot.mouseMove(robot.getMouseX(), robot.getMouseY() - 10);
                    double targetVvalueUp = vvalue - delta;
                    animateScrollPaneVvalue(scrollPane, targetVvalueUp);



                    if(needle.getTranslateY() != -50){
                        //needle.setTranslateY(needle.getTranslateY()-5);
                        transition.setToY(needle.getTranslateY()-25);
                        transition.play();
                    }
                    if(needle2.getTranslateY() != -50){
                        //needle.setTranslateY(needle.getTranslateY()-5);
                        transition2.setToY(needle2.getTranslateY()-25);
                        transition2.play();
                    }

                    break;
                case DOWN:
                    robot.mouseMove(robot.getMouseX(), robot.getMouseY() + 10);
                    //scrollPane.setVvalue(vvalue + delta);
                    double targetVvalueDown = vvalue + delta;
                    animateScrollPaneVvalue(scrollPane, targetVvalueDown);
                    if(needle.getTranslateY() != -50){
                        //needle.setTranslateY(needle.getTranslateY()-5);
                        transition.setToY(needle.getTranslateY()-25);
                        transition.play();
                    }

                    if(needle2.getTranslateY() != 350){
                        //needle.setTranslateY(needle.getTranslateY()-5);
                        transition2.setToY(needle2.getTranslateY()+25);
                        transition2.play();
                    }
                    break;
                case LEFT:

                    //needle.setTranslateY(350);
                    transition.setToY(350);
                    transition.play();
                    break;
                case RIGHT:
                    robot.mouseMove(robot.getMouseX() + 10, robot.getMouseY());
                    if(needle.getTranslateY() != -50){
                        //needle.setTranslateY(needle.getTranslateY()-5);
                        transition.setToY(needle.getTranslateY()-25);
                        transition.play();
                    }


                    System.out.println("v : "+vvalue);
                    if(vvalue > 0.5) {
                        vvalue = 0.5;
                        //scrollPane.setVvalue(vvalue);
                        animateScrollPaneVvalue(scrollPane, vvalue);
                    }
                    else if (vvalue < 0.5) {
                        vvalue = 0.5;
                        //scrollPane.setVvalue(vvalue);
                        animateScrollPaneVvalue(scrollPane, vvalue);
                    }

                case Q:
                    robot.mouseMove(robot.getMouseX(), robot.getMouseY()+1);
                    pl.setTranslateX(pl.getTranslateX()-10);
                    break;
                case S:
                    robot.mouseMove(robot.getMouseX(), robot.getMouseY()+1);
                    pl.setTranslateY(pl.getTranslateY()+10);
                    break;
                case D:
                    robot.mouseMove(robot.getMouseX(), robot.getMouseY()+1);
                    pl.setTranslateX(pl.getTranslateX()+10);
                    break;
                case Z:
                    robot.mouseMove(robot.getMouseX(), robot.getMouseY()+1);
                    pl.setTranslateY(pl.getTranslateY()-10);
                    break;
                default:
                    break;
            }
        });

        stage.setScene(scene);
        //mediaPlayer.play();
        stage.show();
    }

    private ImageView createImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(2000);
        return imageView;
    }


    private void animateScrollPaneVvalue(ScrollPane scrollPane, double targetVvalue) {
        double currentVvalue = scrollPane.getVvalue();
        Duration duration = Duration.millis(380);
        KeyValue keyValue = new KeyValue(scrollPane.vvalueProperty(), targetVvalue);
        KeyFrame keyFrame = new KeyFrame(duration, keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }


    private ScrollPane createScrollPane(Node content) {

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
            double width = 800;
            double height = 100;
            double contentWidth = content.getBoundsInLocal().getWidth();
            double contentHeight = content.getBoundsInLocal().getHeight();

            if (width > contentWidth) {
                content.setScaleX(1.19);
                content.setScaleY(1.19);
            } else if (height > contentHeight) {
                content.setScaleX(1.19);
                content.setScaleY(1.19);
            } else {
                content.setScaleX(2);
                content.setScaleY(2);
            }
        });
        scrollPane.setVvalue(0.5);
        scrollPane.setHvalue(0.5);

        return scrollPane;
    }



}
