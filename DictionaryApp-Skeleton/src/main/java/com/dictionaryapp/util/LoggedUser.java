package com.dictionaryapp.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Getter
@Setter
@NoArgsConstructor

@Component
@SessionScope
public class LoggedUser {

    private Long id;
    private String username;


    public boolean isLogged() {
        return username != null && id != null;
    }

    public void loginUser(Long id, String username) {
        setId(id);
        setUsername(username);
    }

    public void logout() {
        this.id = null;
        this.username = null;
    }
}
