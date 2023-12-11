package bg.softuni.pathfinder.model.entities;

import bg.softuni.pathfinder.model.enums.Level;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "age")
    private int age;
    @Column(name = "email", unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id")}
    )
    private Set<Role> roles;
    @Enumerated(EnumType.STRING)
    private Level level;


}
