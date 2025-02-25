package de.cembers.ceregamebuy.model;

/**
 * Enum für die verschiedenen Zustände, in denen sich ein Spiel befinden kann.
 */
public enum Condition {
   NEW("Neuwertig"),
    USED("Gebraucht"),
    DAMAGED("Beschädigt");


    final String description;

    Condition(String description) {
        this.description = description;

    }
    public String getDescription() {
        return description;
    }

    public static Condition getConditionByDescription(String description){
        for (Condition condition : Condition.values()) {
            if (condition.getDescription().equals(description)){
                return condition;
            }
        }

        return null;
    }
}
