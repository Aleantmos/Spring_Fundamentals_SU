package bg.softuni.pathfinder.model.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "approved")
    private Boolean approved;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "text_content", columnDefinition = "TEXT")
    private String texContent;
    @ManyToOne
    private User author;
    @ManyToOne
    private Route route;

    public Comment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getTexContent() {
        return texContent;
    }

    public void setTexContent(String texContent) {
        this.texContent = texContent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }


}
