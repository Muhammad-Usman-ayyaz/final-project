module com.treasurehuntgamecomplete {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.treasurehuntgamecomplete to javafx.fxml;
    exports com.treasurehuntgamecomplete;
}