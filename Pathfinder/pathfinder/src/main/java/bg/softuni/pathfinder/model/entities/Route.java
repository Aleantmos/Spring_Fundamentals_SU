package bg.softuni.pathfinder.model.entities;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.persistence.*;

import java.util.Set;

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

    public Route() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategories(Set<Category> categories) {
        this.categories.addAll(categories);
    }
}
