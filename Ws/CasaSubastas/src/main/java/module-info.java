module co.edu.uniquindio.casasubastas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens co.edu.uniquindio.casasubastas to javafx.fxml;
    exports co.edu.uniquindio.casasubastas;
    exports co.edu.uniquindio.casasubastas.controllers;
    opens co.edu.uniquindio.casasubastas.controllers to javafx.fxml;
}