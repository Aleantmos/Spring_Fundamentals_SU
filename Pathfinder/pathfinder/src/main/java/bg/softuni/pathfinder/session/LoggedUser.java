package bg.softuni.pathfinder.session;

import org.springframework.stereotype.Component;

@Component
public class LoggedUser {

    private String username;
    private String email;
    private String fullName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void reset() {
        setFullName(null);
        setUsername(null);
        setEmail(null);
    }
}