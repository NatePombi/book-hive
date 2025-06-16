package model;

public enum Genre {

    FICTION("Stories created from the imagination, not presented as fact"),
    NON_FICTION("Based on real facts, people, and events"),
    FANTASY("Features magical worlds, mythical creatures, and supernatural elements"),
    MYSTERY("Centers on solving a crime, puzzle, or unexplained event"),
    THRILLER("Fast-paced stories with suspense, danger, and high stakes");

    private final String description;

    Genre(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Genre getGenre(String genre){
        String cleaned = genre.replace(" ", "_");
        return switch(cleaned.toLowerCase()){
            case "fiction" -> FICTION;
            case "non_fiction" -> NON_FICTION;
            case "fantasy" -> FANTASY;
            case "mystery" -> MYSTERY;
            case "thriller" -> THRILLER;
            default -> throw new IllegalArgumentException();
        };
    }
}
