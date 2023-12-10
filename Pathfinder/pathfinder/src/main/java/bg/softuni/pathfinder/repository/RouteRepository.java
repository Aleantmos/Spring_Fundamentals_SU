package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entities.Route;
import bg.softuni.pathfinder.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {


    List<Route> findAllByCategories_Name(CategoryName categoryName);
}
