package bg.softuni.pathfinder.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Level {
    BEGINNER, INTERMEDIATE, ADVANCED;

    public static List<Level> getLevelEnum() {
        return Arrays.stream(Level.values()).toList();
        //return List.of(BEGINNER, INTERMEDIATE, ADVANCED);
    }
}
