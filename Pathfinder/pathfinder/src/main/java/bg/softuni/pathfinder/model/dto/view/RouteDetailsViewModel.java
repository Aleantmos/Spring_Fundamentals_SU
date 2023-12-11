package bg.softuni.pathfinder.model.dto.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RouteDetailsViewModel {

    private Long id;
    private String name;
    private String level;
    private String description;
    private String videoUrl;
    private String authorName;
    private List<CommentViewModel> commentViewModelList;

    //TODO pass coordinates
}
