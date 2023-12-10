package bg.softuni.pathfinder.model.dto.binding;

import bg.softuni.pathfinder.model.entities.User;
import bg.softuni.pathfinder.model.enums.CategoryName;
import bg.softuni.pathfinder.model.enums.Level;
import bg.softuni.pathfinder.validation.anotation.FileAnnotation;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor

public class AddRouteBindingModel {

    @Size(min = 3, max = 20, message = "Name length must be more than 3 characters")
    private String name;
    @Size(min = 3, max = 20, message = "Description length must be more than 5 characters")
    private String description;
    @FileAnnotation(contentTypes = "text/xml")
    private MultipartFile gpxCoordinates;
    private Level level;

    @Pattern(regexp = "https:\\/\\/www\\.youtube\\.com\\/watch\\?v=.*", message = "Invalid youtube url provided")
    private String videoUrl;
    private Set<CategoryName> categories;
    private User author;



}
