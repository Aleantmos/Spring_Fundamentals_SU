package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.exception.RouteNotFoundException;
import bg.softuni.pathfinder.model.dto.binding.AddRouteBindingModel;
import bg.softuni.pathfinder.model.dto.view.RouteDetailsViewModel;
import bg.softuni.pathfinder.model.dto.view.RouteViewModel;
import bg.softuni.pathfinder.model.entities.Route;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private static final String BASE_GPX_COORDINATES_PATH = ".\\src\\main\\resources\\coordinates\\";
    private final LoggedUser loggedUser;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public void add(AddRouteBindingModel addRouteBindingModel) {

        Route route = modelMapper.map(addRouteBindingModel, Route.class);

        String filePath = getFilePath(route.getName());

        boolean isUploaded = uploadGpxCoordinates(addRouteBindingModel.getGpxCoordinates(), filePath);

        if (isUploaded) {
            route.setGpxCoordinates(filePath);
        }




        routeRepository.save(route);
    }

    private boolean uploadGpxCoordinates(MultipartFile file, String filePath) {
        try {
            File newFile = new File(BASE_GPX_COORDINATES_PATH + filePath);
            newFile.getAbsoluteFile().mkdirs();
            newFile.createNewFile();

            OutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(file.getBytes());
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public List<RouteViewModel> getAllRoutes() {
        return routeRepository.findAll()
                .stream()
                .map(route -> modelMapper.map(route, RouteViewModel.class))
                .toList();
    }

    @Override
    public RouteDetailsViewModel getDetails(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("No such route"));

        RouteDetailsViewModel routeDetailsViewModel = modelMapper.map(route, RouteDetailsViewModel.class);
        routeDetailsViewModel.setAuthorName(route.getAuthor().getFullName());

        return routeDetailsViewModel;
    }

    private String getFilePath(String routeName) {
        String pathPattern = "%s\\%s_%s.xml";
        return String.format(pathPattern,
                loggedUser.getUsername(),
                transformRouteName(routeName),
                UUID.randomUUID());
    }

    private String transformRouteName(String routeName) {
        return routeName.toLowerCase()
                .replaceAll("\\s+", "_");
    }
}
