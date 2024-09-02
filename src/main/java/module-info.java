module org.unibl.etf.pj2.emobility {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens org.unibl.etf.pj2.emobility to javafx.fxml;
    exports org.unibl.etf.pj2.emobility;
}