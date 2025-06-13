package api_rest.app.services.interfaces;

import api_rest.app.models.dto.UserDTO;
import api_rest.app.models.dto.UserLoginDTO;
import api_rest.app.models.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    List<User> listAll();

    User save(UserDTO userDto);

    User registerUser(UserDTO userDTO);

    boolean validateUserInfo(User user, UserLoginDTO userlogin);

    User findById(UUID id);

    Optional<User> findByEmail(String email);

    void delete(User user);

    boolean existsById(UUID id);
}
