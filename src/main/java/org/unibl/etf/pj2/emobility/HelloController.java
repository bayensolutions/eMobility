package org.unibl.etf.pj2.emobility;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class HelloController {

    @FXML
    private GridPane gridPane;

    private static final int SIZE = 20; // Veličina grida

    @FXML
    public void initialize() {
        // Dinamički kreiramo TextFieldove i dodajemo ih u GridPane
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                TextField cell = new TextField();
                cell.setPrefSize(50, 50); // Veličina svakog polja
                cell.setEditable(false); // Onemogućavamo editovanje
                cell.getStyleClass().add("text-field");

                if (shouldBeColored(row, col)) {
                    cell.getStyleClass().add("highlight"); // Dodajemo klasu za bojenje
                }

                if (row == 5 && col == 5) {
                    cell.setText("T");
                }

                // Dodajemo TextField u GridPane
                gridPane.add(cell, col, row);
            }
        }
    }


    private boolean shouldBeColored(int row, int col) {
        return (row > 4 && row < 15 && col > 4 && col < 15);
    }
}
