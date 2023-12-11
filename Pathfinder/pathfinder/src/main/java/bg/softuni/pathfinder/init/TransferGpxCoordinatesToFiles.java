package bg.softuni.pathfinder.init;

import bg.softuni.pathfinder.model.entities.Route;
import bg.softuni.pathfinder.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransferGpxCoordinatesToFiles implements CommandLineRunner {
    private final RouteRepository routeRepository;
    private static final String BASE_GPX_COORDINATES_PATH = ".\\src\\main\\resources\\coordinates\\";
    @Value("${pathfinder.gpx-coordinates.migrate}")
    private Boolean shouldMigrate;

    @Override
    public void run(String... args) {
        if (shouldMigrate) {
            List<Route> routes = this.routeRepository.findAll().stream()
                    .filter(route -> route.getGpxCoordinates().startsWith("<?xml"))
                    .collect(Collectors.toList());

            routes.forEach(route -> {
                String gpxCoordinatesAsString = route.getGpxCoordinates();
                String filePath = getFilePath(route.getName(), route.getAuthor().getUsername());
                try {
                    File file = new File(BASE_GPX_COORDINATES_PATH + filePath);
                    file.getParentFile().mkdirs();
                    file.createNewFile();

                    OutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(gpxCoordinatesAsString.getBytes());

                    route.setGpxCoordinates(filePath);
                    routeRepository.save(route);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private String getFilePath(String routeName, String username) {
        String pathPattern = "%s\\%s_%s.xml";
        return String.format(pathPattern,
                username,
                transformRouteName(routeName),
                UUID.randomUUID());
    }

    private String transformRouteName(String routeName) {
        return routeName.toLowerCase()
                .replaceAll("\\s+", "_")
                .replaceAll("\"", "");
    }
}
