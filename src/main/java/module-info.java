module org.example.tabuleirosudoko {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tabuleirosudoko  to javafx.fxml;
    exports org.example.tabuleirosudoko ;
}
