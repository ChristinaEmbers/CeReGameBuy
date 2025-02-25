package de.cembers.ceregamebuy.logic.db;

import de.cembers.ceregamebuy.model.Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse mit direktem Datenbankzugriff, die sich um alle Zugriffe kümmert.
 */
public class DbManager {
    //region Konstanten
    private static final String DB_LOCAL_SERVER_IP_ADDRESS = "localhost/";
    private static final String DB_LOCAL_NAME = "regamebuy";

    private static final String DB_LOCAL_CONNECTION_URL = "jdbc:mariadb://" + DB_LOCAL_SERVER_IP_ADDRESS + DB_LOCAL_NAME;

    private static final String DB_LOCAL_USER_NAME = "root";
    private static final String DB_LOCAL_USER_PW = "";

    //endregion

    //region Deklaration und Initialisierung
    private static DbManager instance;
    private DaoGame daoGame;
    //endregion

    //region Konstruktoren
    private DbManager() {
        daoGame = new DaoGame();
    }
    //endregion

    //region getInstance
    public static synchronized DbManager getInstance() {

        if (instance == null) {
            instance = new DbManager();
        }

        return instance;

    }
    //endregion

    //region Verbindung zur Datenbank
    private Connection getDatabaseConnection() throws Exception {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_LOCAL_CONNECTION_URL, DB_LOCAL_USER_NAME, DB_LOCAL_USER_PW);
        } catch (SQLNonTransientConnectionException sqlNoConnectionEx) {
            throw new Exception("Keine Datenbankverbindung");
        }

        return connection;
    }

    public boolean isDatabaseOnline() {
        boolean isOnline = true;
        try {
            this.getDatabaseConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
            isOnline = false;
        }
        return isOnline;
    }
    //endregion
    
    //region CRUD

    public void insertGame(Game gameToInsert) {
        try {
            if (this.isDatabaseOnline()) {
                this.daoGame.create(this.getDatabaseConnection(), gameToInsert);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Game> readGames() {
        List<Game> games = new ArrayList<>();

        try {
            if (this.isDatabaseOnline()) {
                games = daoGame.readAll(this.getDatabaseConnection());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return games;
    }

    public List<Game> getSortedData(String selectionCriteria, boolean ascendingOrder)
    {
        List<Game> games = new ArrayList<>();
        try {
            if (this.isDatabaseOnline()) {
                games = daoGame.getSortedGames(this.getDatabaseConnection(), selectionCriteria, ascendingOrder);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return games;
    }

    public void updateGame(Game gameToUpdate) {
        try {
            if (this.isDatabaseOnline()) {
                this.daoGame.update(this.getDatabaseConnection(), gameToUpdate);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteGame(Game gameToDelete) {
        try {
            if (this.isDatabaseOnline()) {
                this.daoGame.delete(this.getDatabaseConnection(), gameToDelete);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
    
    //endregion
}
