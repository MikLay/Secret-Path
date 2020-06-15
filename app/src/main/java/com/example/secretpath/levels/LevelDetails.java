package com.example.secretpath.levels;

public class LevelDetails {
    private final int id;
    private final String name;
    private final String[] hints;

    public LevelDetails(int id, String name, String[] hints) {
        this.id = id;
        this.name = name;
        this.hints = hints;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getHints() {
        return hints;
    }
}
