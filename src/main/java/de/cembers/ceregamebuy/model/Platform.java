package de.cembers.ceregamebuy.model;

/**
 * Enum für die Verschiedenen Plattformen, auf denen Spiele gespielt werden können
 */
public enum Platform {
    //region Platform Werte
    NINTENDODS("Nintendo DS"),
    WII("Wii"),
    COMPUTER("Computer"),
    PLAYSTATION4("Playstation 4"),
    XBOX("XBOX"),
    GAMECUBE("Game Cube"),
    SWITCH("Switch");

    //endregion

    final String description;

    Platform(String description) {
        this.description = description;

    }
    public String getDescription() {
        return description;
    }

    public static Platform getPlatformByDescription(String description){
        for (Platform platform : Platform.values()) {
            if (platform.getDescription().equals(description)){
                return platform;
            }
        }

        return null;
    }

}
