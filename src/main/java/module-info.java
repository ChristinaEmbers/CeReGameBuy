module de.cembers.ceregamebuy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens de.cembers.ceregamebuy to javafx.fxml;
    exports de.cembers.ceregamebuy;
    exports de.cembers.ceregamebuy.gui;
    opens de.cembers.ceregamebuy.gui to javafx.fxml;
}