package de.cembers.ceregamebuy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Datenhaltungsklasse, die ein Videospiel darstellt
 */
public class Game {
    //region Konstanten
    public static final String NEW_ID_TEXT = "wird bei Neuanlage automatisch gesetzt";
    private static final String NOT_YET_SET = "NichtGesetzt";
    public static final Integer DEFAULT_NUMBER = -1;
    private static final double DEFAULT_PRICE = 0.0;
    //endregion

    //region Variablen

    private int gameId;
    private String title;
    private String publisher;
    private Platform platform;
    private Genre genre;
    private Integer releaseYear;
    private Condition condition;
    private double price;
    private String description;

    //endregion


    //region Konstruktoren
    public Game() {
        this.gameId = DEFAULT_NUMBER;
        this.title = NOT_YET_SET;
        this.publisher = NOT_YET_SET;
        this.platform = Platform.COMPUTER;
        this.genre = Genre.RPG;
        this.releaseYear = DEFAULT_NUMBER;
        this.condition = Condition.NEW;
        this.price = DEFAULT_PRICE;
        this.description = NOT_YET_SET;
    }


    public Game(int gameId,
                String title,
                String publisher,
                Platform platform,
                Genre genre,
                int releaseYear,
                Condition condition,
                double price,
                String description) {

        this.gameId = gameId;
        this.title = title;
        this.publisher = publisher;
        this.platform = platform;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.condition = condition;
        this.price = price;
        this.description = description;
    }
    //endregion


    //region getter und setter

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion

    //region String Methoden
    public String getMinimalInformation()
    {
        return String.format("%s - %s - %s - %s", getGameId(), getTitle(), getPlatform().getDescription(), getCondition().getDescription());
    }


    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", platform='" + platform + '\'' +
                ", genre=" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                ", condition=" + condition +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    //endregion
}
