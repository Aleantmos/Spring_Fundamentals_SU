package bg.softuni.pathfinder.model.entities;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    private User author;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToMany
    private Set<Category> categories;
    @OneToMany(mappedBy = "route")
    private List<Comment> comments;


}
