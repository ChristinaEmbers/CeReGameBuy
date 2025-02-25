package de.cembers.ceregamebuy.model;

/**
 * Enum für die verschiedenen Genres, die ein Spiel haben kann
 */
public enum Genre {
    //region Genre Werte
    ACTION("Action"),
    ABENTEUER("Abenteuer"),
    RPG("Rollenspiel"),
    SIMULATION("Simulation"),
    STRATEGIE("Strategie"),
    SHOOTER("Shooter"),
    SPORT("Sport"),
    PUZZLE("Puzzle"),
    HORROR("Horror"),
    RENNSPIEL("Rennspiel"),
    KAMPF("Kampfspiel"),
    SANDBOX("Sandbox"),
    SURVIVAL("Überlebensspiel"),
    MUSIK("Musik/Rhythmus"),
    FPS("Fps"),
    PARTY("Partyspiel");

    //endregion

    final String description;

    Genre(String description) {
        this.description = description;

    }
    public String getDescription() {
        return description;
    }

    public static Genre getGenreByDescription(String description){
        for (Genre genre : Genre.values()) {
            if (genre.getDescription().equals(description)){
                return genre;
            }
        }

        return null;
    }

}
