package api_rest.app.services.implement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api_rest.app.models.dao.UserDAO;
import api_rest.app.models.dto.UserDTO;
import api_rest.app.models.dto.UserLoginDTO;
import api_rest.app.models.entity.User;
import api_rest.app.services.interfaces.IUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserDAO userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserDAO userDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public User registerUser(UserDTO userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .creation_date(LocalDateTime.now())
                .build();
        return userDao.save(user);
    }

    public boolean validateUserInfo(User user, UserLoginDTO userlogin) {

        boolean isEmailsEquals = user.getEmail().equals(userlogin.getEmail());
        boolean isPasswordsEquals = passwordEncoder.matches(userlogin.getPassword(), user.getPassword());

        return isEmailsEquals && isPasswordsEquals;

    }

    @Transactional
    @Override
    public User save(UserDTO userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .creation_date(LocalDateTime.now())
                .build();

        return userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(UUID id) {
        return userDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean existsById(UUID id) {
        return userDao.existsById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

}
