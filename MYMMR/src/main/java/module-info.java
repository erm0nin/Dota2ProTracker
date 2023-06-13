module com.example.mymmr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mymmr to javafx.fxml;
    exports com.example.mymmr;
}