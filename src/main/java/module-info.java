module com.example.powerlifting_app {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
            requires com.almasb.fxgl.all;
    
    opens com.example.powerlifting_app to javafx.fxml;
    exports com.example.powerlifting_app;
    exports com.example.powerlifting_app.Core;
    opens com.example.powerlifting_app.Core to javafx.fxml;
    exports com.example.powerlifting_app.GUI;
    opens com.example.powerlifting_app.GUI to javafx.fxml;
}