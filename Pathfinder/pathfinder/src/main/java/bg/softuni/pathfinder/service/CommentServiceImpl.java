package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.exception.RouteNotFoundException;
import bg.softuni.pathfinder.exception.UserNotFoundException;
import bg.softuni.pathfinder.model.dto.binding.CreateCommentBindingModel;
import bg.softuni.pathfinder.model.entities.Comment;
import bg.softuni.pathfinder.model.entities.Route;
import bg.softuni.pathfinder.model.entities.User;
import bg.softuni.pathfinder.repository.CommentRepository;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, RouteRepository routeRepository, UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(CreateCommentBindingModel createCommentBindingModel) {
        Optional<Route> optionalRoute = routeRepository.findById(createCommentBindingModel.getRouteId());

        if (optionalRoute.isEmpty()) {
            throw new RouteNotFoundException("Route not found");
        }

        Optional<User> optionalUser = userRepository.findByUsername(loggedUser.getUsername());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with username " + loggedUser.getUsername() + " was not found" );
        }

        Route route = optionalRoute.get();
        User user = optionalUser.get();

        //Comment comment = modelMapper.map(createCommentBindingModel, Comment.class);
        Comment comment = new Comment();
        comment.setRoute(route);
        comment.setCreated(LocalDateTime.now());
        comment.setAuthor(user);

        commentRepository.save(comment);
    }
}
