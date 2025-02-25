package de.cembers.ceregamebuy.logic.db;

import de.cembers.ceregamebuy.gui.GameController;
import de.cembers.ceregamebuy.model.Condition;
import de.cembers.ceregamebuy.model.Game;
import de.cembers.ceregamebuy.model.Genre;
import de.cembers.ceregamebuy.model.Platform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data Access Object, in dem die SQL Befehle zusammengebaut und befüllt werden, die an die Datenbank übergeben werden
 */
public class DaoGame implements Dao<Game> {
    //region Konstanten
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PUBLISHER = "publisher";
    private static final String COLUMN_PLATFORM = "platform";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_RELEASE_YEAR = "releaseYear";
    private static final String COLUMN_GAME_CONDITION = "gameCondition";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String ASCENDING_ORDER="ASC";
    private static final String DESCENDING_ORDER="DESC";

    public static final String INVALID_CRITERIA = "Ungültiges Kriterium: ";
    //endregion

    //region Prepared Statements
    private static final String SELECT_ALL_GAMES = "SELECT * FROM games";
    private static final String SELECT_ALL_GAMES_IN_ORDER = "SELECT * FROM games ORDER BY";
    private static final String INSERT_GAME =
            "INSERT INTO `games`(" +
                    "`title`, " +
                    "`publisher`, " +
                    "`platform`, " +
                    "`genre`, " +
                    "`releaseYear`, " +
                    "`gameCondition`, " +
                    "`price`, " +
                    "`description`) " +
                    "VALUES (?,?,?,?,?,?,?,?)";

    private static final String UPDATE_GAME =
            "UPDATE `games` SET " +
                    "`title`=?, " +
                    "`publisher`=?, " +
                    "`platform`=?, " +
                    "`genre`=?, " +
                    "`releaseYear`=?, " +
                    "`gameCondition`=?, " +
                    "`price`=?, " +
                    "`description`=? " +
                    "WHERE `id`=?";

    private static final String DELETE_GAME =
            "DELETE FROM `games` WHERE `id`=?";

    //endregion

    //region CRUD
    @Override
    public void create(Connection connection, Game modelToInsert) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_GAME)) {
            final int statementParameterIndexTitle = 1;
            final int statementParameterIndexPublisher = 2;
            final int statementParameterIndexPlatform = 3;
            final int statementParameterIndexGenre = 4;
            final int statementParameterIndexReleaseYear = 5;
            final int statementParameterIndexGameCondition = 6;
            final int statementParameterIndexPrice = 7;
            final int statementParameterIndexDescription = 8;

            statement.setString(statementParameterIndexTitle, modelToInsert.getTitle());
            statement.setString(statementParameterIndexPublisher, modelToInsert.getPublisher());
            statement.setString(statementParameterIndexPlatform, modelToInsert.getPlatform().name());
            statement.setString(statementParameterIndexGenre, modelToInsert.getGenre().name());
            statement.setInt(statementParameterIndexReleaseYear, modelToInsert.getReleaseYear());
            statement.setString(statementParameterIndexGameCondition, modelToInsert.getCondition().name());
            statement.setDouble(statementParameterIndexPrice, modelToInsert.getPrice());
            statement.setString(statementParameterIndexDescription, modelToInsert.getDescription());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Game> readAll(Connection connection) {
        List<Game> games = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GAMES)) {
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Game gameFromDatabaseTable = getModelFromResultSet(resultSet);
                games.add(gameFromDatabaseTable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }
    @Override
    public void update(Connection connection, Game modelToUpdate) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_GAME)) {

            final int statementParameterIndexTitle = 1;
            final int statementParameterIndexPublisher = 2;
            final int statementParameterIndexPlatform = 3;
            final int statementParameterIndexGenre = 4;
            final int statementParameterIndexReleaseYear = 5;
            final int statementParameterIndexGameCondition = 6;
            final int statementParameterIndexPrice = 7;
            final int statementParameterIndexDescription = 8;
            final int statementParameterIndexId = 9;

            statement.setString(statementParameterIndexTitle, modelToUpdate.getTitle());
            statement.setString(statementParameterIndexPublisher, modelToUpdate.getPublisher());
            statement.setString(statementParameterIndexPlatform, modelToUpdate.getPlatform().name());
            statement.setString(statementParameterIndexGenre, modelToUpdate.getGenre().name());
            statement.setInt(statementParameterIndexReleaseYear, modelToUpdate.getReleaseYear());
            statement.setString(statementParameterIndexGameCondition, modelToUpdate.getCondition().name());
            statement.setDouble(statementParameterIndexPrice, modelToUpdate.getPrice());
            statement.setString(statementParameterIndexDescription, modelToUpdate.getDescription());
            statement.setInt(statementParameterIndexId, modelToUpdate.getGameId());

            statement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Connection connection, Game modelToDelete) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_GAME)) {

            final int statementParameterIndexId = 1;

            statement.setInt(statementParameterIndexId, modelToDelete.getGameId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Game getModelFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(COLUMN_ID);
        String title = resultSet.getString(COLUMN_TITLE);
        String publisher = resultSet.getString(COLUMN_PUBLISHER);
        Platform platform = Platform.valueOf(resultSet.getString(COLUMN_PLATFORM)); // Platform-Enum
        Genre genre = Genre.valueOf(resultSet.getString(COLUMN_GENRE));       // Genre-Enum
        int releaseYear = resultSet.getInt(COLUMN_RELEASE_YEAR);
        Condition condition = Condition.valueOf(resultSet.getString(COLUMN_GAME_CONDITION)); // Condition-Enum
        double price = resultSet.getDouble(COLUMN_PRICE);
        String description = resultSet.getString(COLUMN_DESCRIPTION);

        return new Game(
                id,
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

    //endregion

    //region Sortiermethoden
    public List<Game> getSortedGames(Connection connection, String selectionCriteria, boolean ascendingOrder) {
        List<Game> games = new ArrayList<>();
        String criteria = "";
        switch (selectionCriteria) {
            case GameController.GAME_ID -> criteria = COLUMN_ID;
            case GameController.GAME_TITLE-> criteria = COLUMN_TITLE;
            case GameController.GAME_PUBLISHER -> criteria = COLUMN_PUBLISHER;
            case GameController.GAME_PLATFORM -> criteria = COLUMN_PLATFORM;
            case GameController.GAME_GENRE -> criteria = COLUMN_GENRE;
            case GameController.GAME_CONDITION -> criteria = COLUMN_GAME_CONDITION;
            case GameController.GAME_RELEASE_YEAR -> criteria = COLUMN_RELEASE_YEAR;
            case GameController.GAME_PRICE -> criteria = COLUMN_PRICE;
            default -> throw new IllegalArgumentException(INVALID_CRITERIA + selectionCriteria);
        }

        String order = "";
        if (ascendingOrder) {
            order = ASCENDING_ORDER;
        } else {
            order = DESCENDING_ORDER;
        }


        if(true)
        {
            //tue Dinge
        }
        else
        {
            //tue andere Dinge
        }

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GAMES_IN_ORDER +" "+ criteria +" "+ order)) {
            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Game gameFromDatabaseTable = getModelFromResultSet(resultSet);
                games.add(gameFromDatabaseTable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }
    //endregion
}
