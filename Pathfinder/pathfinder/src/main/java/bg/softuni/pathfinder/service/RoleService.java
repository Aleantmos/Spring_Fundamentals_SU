package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.entities.Role;

public interface RoleService {
    Role getRoleByName(String name);

    int getRoleRepoCount();

    void saveRole(Role role);
}
