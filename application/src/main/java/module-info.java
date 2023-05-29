module part13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    opens SavingsCalculator to javafx.fxml;
    exports SavingsCalculator;
}
