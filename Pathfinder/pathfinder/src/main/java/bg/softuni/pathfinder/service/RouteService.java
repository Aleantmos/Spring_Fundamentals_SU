package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.binding.AddRouteBindingModel;
import bg.softuni.pathfinder.model.dto.binding.UploadPictureRouteBindingModel;
import bg.softuni.pathfinder.model.dto.view.RouteCategoryViewModel;
import bg.softuni.pathfinder.model.dto.view.RouteDetailsViewModel;
import bg.softuni.pathfinder.model.dto.view.RouteViewModel;
import bg.softuni.pathfinder.model.enums.CategoryName;

import java.util.List;

public interface RouteService {

    void add(AddRouteBindingModel addRouteBindingModel);

    List<RouteViewModel> getAllRoutes();

    RouteDetailsViewModel getDetails(Long id);

    void uploadPicture(UploadPictureRouteBindingModel uploadPictureRouteBindingModel);

    List<RouteCategoryViewModel> getAllByCategory(CategoryName categoryName);
}
