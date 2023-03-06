module ma.ensaj.dem1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires jdk.jdeps;


    opens ma.ensaj.dem1 to javafx.fxml;
    exports ma.ensaj.dem1;
    exports ma.ensaj.dem1.view;
    opens ma.ensaj.dem1.view to javafx.fxml;
}