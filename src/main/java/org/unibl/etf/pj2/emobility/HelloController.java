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

                // Oboji određena polja u plavo
                if (shouldBeColored(row, col)) {
                    cell.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                }

                gridPane.add(cell, col, row); // Dodajemo TextField u GridPane
            }
        }
    }

    // Metoda koja određuje koja polja treba obojiti
    private boolean shouldBeColored(int row, int col) {
        // Primer: Oboj ćelije u prvoj i poslednjoj koloni, i prvoj i poslednjoj vrsti
        return (row > 4 && row <15 && col>4 && col<15);
    }
}
