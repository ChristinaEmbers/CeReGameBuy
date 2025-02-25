package de.cembers.ceregamebuy.gui;

import de.cembers.ceregamebuy.model.Game;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.util.Callback;

/**
 * Kümmert sich um das Aufrufen und Aufbauen der Zellen für den Listview
 */
public class GameCellFactory implements Callback<ListView<Game>, ListCell<Game>> {

    @Override
    public ListCell<Game> call(ListView<Game> param) {return new GameListViewCell();}
}
