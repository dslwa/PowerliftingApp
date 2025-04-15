module com.example.powerlifting_app {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
    
    opens com.example.powerlifting_app to javafx.fxml;
    exports com.example.powerlifting_app;
    exports com.example.powerlifting_app.Core;
    opens com.example.powerlifting_app.Core to javafx.fxml;
    exports com.example.powerlifting_app.GUI;
    opens com.example.powerlifting_app.GUI to javafx.fxml;
}