package bg.softuni.pathfinder.model.enums;

import java.util.Arrays;
import java.util.List;

public enum CategoryName {
    PEDESTRIAN, BICYCLE, MOTORCYCLE, CAR;

    public static List<CategoryName> getCategoryEnum() {
        return Arrays.stream(CategoryName.values()).toList();
    }
}
