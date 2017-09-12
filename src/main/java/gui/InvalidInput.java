package gui;


import javafx.scene.control.Alert;

class InvalidInput {
    InvalidInput(String s) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
}
