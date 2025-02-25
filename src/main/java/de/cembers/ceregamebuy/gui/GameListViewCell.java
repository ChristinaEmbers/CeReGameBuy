package de.cembers.ceregamebuy.gui;

import de.cembers.ceregamebuy.model.Game;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

/**
 * Repräsentiert eine Zelle des Listviews, die erstellt wird und die Aktualisierung des Inhalts vornimmt
 */
public class GameListViewCell extends ListCell<Game> {

    @Override
    protected void updateItem(Game gameToShow, boolean empty) {
        super.updateItem(gameToShow, empty);

        if (empty || gameToShow == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(gameToShow.getMinimalInformation());
            setFont(new Font("Consolas", 16));
        }
    }
}
