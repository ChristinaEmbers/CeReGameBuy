package de.cembers.ceregamebuy.gui;

import de.cembers.ceregamebuy.logic.db.DbManager;
import de.cembers.ceregamebuy.model.Condition;
import de.cembers.ceregamebuy.model.Game;
import de.cembers.ceregamebuy.model.Genre;
import de.cembers.ceregamebuy.model.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Diese Klasse ist zuständig für die Interaktionen mit der GUI
 */
public class GameController implements Initializable {
    public static final String GAME_ID = "Id";
    public static final String GAME_TITLE = "Titel";
    public static final String GAME_PUBLISHER = "Publisher";
    public static final String GAME_PLATFORM = "Plattform";
    public static final String GAME_GENRE = "Genre";
    public static final String GAME_CONDITION = "Zustand";
    public static final String GAME_RELEASE_YEAR = "Veröffentlichungsjahr";
    public static final String GAME_PRICE = "Preis";
    //region Konstanten
    private static final int GAME_DEFAULT_RELEASE_YEAR = 2000;
    private static final double GAME_DEFAULT_PRICE = 19.99;
    private static final List<String> GAME_SORTING_CRITERIA = Arrays.asList(
            GAME_ID,
            GAME_TITLE,
            GAME_PUBLISHER,
            GAME_PLATFORM,
            GAME_GENRE,
            GAME_CONDITION,
            GAME_RELEASE_YEAR,
            GAME_PRICE);

    //endregion

    //region Deklaration und Initialisierung
    @FXML
    private ListView<Game> games;
    private GameCellFactory gameCellFactory;
    private Game currentSelectedGame;

    @FXML
    private TextField gameId;
    @FXML
    private TextField gameTitel;
    @FXML
    private TextField gamePublisher;
    @FXML
    private ComboBox<Platform> gamePlatformSelection;
    @FXML
    private ComboBox<Genre> gameGenreSelection;
    @FXML
    private Spinner<Integer> gameReleaseYear;
    @FXML
    private ComboBox<Condition> gameConditionSelection;
    @FXML
    private Spinner<Double> gamePrice;
    @FXML
    private TextArea gameDescription;

    @FXML
    private ComboBox<String> gameSortingCriteriaSelection;
    @FXML
    private RadioButton gameAscendingSelection;
    @FXML
    private ToggleGroup toggleSortierreihenfolge;

    //endregion


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updateListViewGames();

        updateGamePlatformSelection();
        updateGameGenreSelection();
        updateGameConditionSelection();
        updateGameSortingSelection();

        addListeners();

    }

    private void updateListViewGames() {
        this.gameCellFactory = new GameCellFactory();
        this.games.setCellFactory(this.gameCellFactory);

        this.games.getItems().clear();
        this.games.setItems(
                FXCollections.observableArrayList(
                        DbManager.getInstance().readGames()
                )
        );
        this.games.getSelectionModel().selectedItemProperty().addListener(getSelectionListener());
    }

    private ChangeListener<Game> getSelectionListener() {
        return (observable, oldGame, newGame) -> {
            if (newGame != null) {
                this.currentSelectedGame = newGame;

                this.gameId.setText(String.valueOf(newGame.getGameId()));
                this.gameTitel.setText(newGame.getTitle());
                this.gamePublisher.setText(newGame.getPublisher());
                this.gamePlatformSelection.setValue(newGame.getPlatform());
                this.gameGenreSelection.setValue(newGame.getGenre());
                this.gameReleaseYear.getValueFactory().setValue(newGame.getReleaseYear());
                this.gameConditionSelection.setValue(newGame.getCondition());
                this.gamePrice.getValueFactory().setValue(newGame.getPrice());
                this.gameDescription.setText(newGame.getDescription());

            }
        };

    }

    //region Comboboxen fuellen
    private void updateGamePlatformSelection() {
        this.gamePlatformSelection.setItems(
                FXCollections.observableArrayList(
                        Platform.values()
                )
        );
        this.gamePlatformSelection.setValue(Platform.COMPUTER);
    }

    private void updateGameGenreSelection() {
        this.gameGenreSelection.setItems(
                FXCollections.observableArrayList(
                        Genre.values()
                )
        );
        this.gameGenreSelection.setValue(Genre.RPG);

    }

    private void updateGameConditionSelection() {
        this.gameConditionSelection.setItems(
                FXCollections.observableArrayList(
                        Condition.values()
                )
        );
        this.gameConditionSelection.setValue(Condition.NEW);
    }

    private void updateGameSortingSelection() {
        this.gameSortingCriteriaSelection.setItems(
                FXCollections.observableArrayList(GAME_SORTING_CRITERIA)
        );
        this.gameSortingCriteriaSelection.setValue(GAME_SORTING_CRITERIA.get(0));
    }
    //endregion

    //region CRUD
    @FXML
    private void submit() {
        Game gameFromUi = getGameFromUi();

        if (gameFromUi == null) {
            showInputIsNotFilledAlert();
        } else {

            if (this.currentSelectedGame != null) {
                DbManager.getInstance().updateGame(gameFromUi);
            }

            this.resetGui();
        }
    }

    @FXML
    private void create() {
        Game gameFromUi = getGameFromUi();

        if (gameFromUi == null) {
            showInputIsNotFilledAlert();
        } else {
            DbManager.getInstance().insertGame(gameFromUi);
        }

        this.resetGui();
    }


    @FXML
    private void delete() {
        if (this.currentSelectedGame != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Löschen bestätigen");
            alert.setHeaderText(String.format("Möchten Sie das Spiel %s wirklich löschen?", this.currentSelectedGame.getMinimalInformation()));

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                DbManager.getInstance().deleteGame(this.currentSelectedGame);

                this.resetGui();
            }
        }
    }
    //endregion

    //region Sortierfunktionen
    private void orderListView(String newSelection) {
        this.games.getSelectionModel().selectedItemProperty().addListener(getSelectionListener());
        boolean ascendingOrder = gameAscendingSelection.isSelected();
        this.gameCellFactory = new GameCellFactory();
        this.games.setCellFactory(this.gameCellFactory);

        this.games.getItems().clear();
        this.games.setItems(
                FXCollections.observableArrayList(
                        DbManager.getInstance().getSortedData(newSelection, ascendingOrder)
                )
        );

    }

    private void addListeners() {
        gameSortingCriteriaSelection.getSelectionModel().selectedItemProperty().
                addListener((observeable, oldSelection, newSelection) ->
                        orderListView(newSelection));

        toggleSortierreihenfolge.selectedToggleProperty().addListener((
                observable, oldToggle, newToggle) ->
                orderListView(gameSortingCriteriaSelection.getValue()));
    }
    //endregion

    private Game getGameFromUi() {
        Game gameFromGui = null;
        String id = gameId.getText();
        String title = gameTitel.getText();
        String publisher = gamePublisher.getText();
        String description = gameDescription.getText();

        String[] inputData = {
                id,
                title,
                publisher,
                description
        };

        if (this.isInputDataFilled(inputData)) {
            Platform platform = gamePlatformSelection.getValue();
            Genre genre = gameGenreSelection.getValue();
            Integer releaseYear = gameReleaseYear.getValue();
            Condition condition = gameConditionSelection.getValue();
            Double price = gamePrice.getValue();

            gameFromGui = new Game(
                    Integer.parseInt(id),
                    title,
                    publisher,
                    platform,
                    genre,
                    releaseYear,
                    condition,
                    price,
                    description
            );
        }

        return gameFromGui;
    }

    private boolean isInputDataFilled(String[] inputData) {
        boolean inputDataIsFilled = true;
        int index = 0;

        while (inputDataIsFilled && index < inputData.length) {
            inputDataIsFilled = !inputData[index].isBlank();
            ++index;
        }
        return inputDataIsFilled;
    }

    private boolean isCorrectDataType() {
        boolean result = true;
        try {

        } catch (Exception e) {
        }

        return result;

    }

    private void showInputIsNotFilledAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alles ausfüllen");
        alert.setHeaderText(null);
        alert.setContentText("Bitte füllen Sie alle Eingabefelder aus.");
        alert.show();
    }

    private void resetGui() {
        updateListViewGames();

        this.currentSelectedGame = null;

        clearInput();
    }

    private void clearInput() {
        this.games.getSelectionModel().clearSelection();

        this.gameId.setText(Game.NEW_ID_TEXT);
        this.gameTitel.clear();
        this.gamePublisher.clear();
        this.gamePlatformSelection.getSelectionModel().selectFirst();
        this.gameGenreSelection.getSelectionModel().selectFirst();
        this.gameReleaseYear.getValueFactory().setValue(GAME_DEFAULT_RELEASE_YEAR);
        this.gameConditionSelection.getSelectionModel().selectFirst();
        this.gamePrice.getValueFactory().setValue(GAME_DEFAULT_PRICE);
        this.gameDescription.clear();
    }
}