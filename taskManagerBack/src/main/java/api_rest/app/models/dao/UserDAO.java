package api_rest.app.models.dao;

import api_rest.app.models.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO extends CrudRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    List<User> findAll();
}
