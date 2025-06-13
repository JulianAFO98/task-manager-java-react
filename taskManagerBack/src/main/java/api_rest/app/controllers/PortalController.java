package api_rest.app.controllers;

import api_rest.app.models.dto.UserDTO;
import api_rest.app.models.dto.UserLoggedDTO;
import api_rest.app.models.dto.UserLoginDTO;
import api_rest.app.models.entity.User;
import api_rest.app.models.payload.MessageResponse;
import api_rest.app.services.implement.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Optional;
import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortalController {

    private final UserService userService;

    PortalController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(MessageResponse.builder()
                    .message("Datos de usuario incorrectos").object(null).status("error").build());
        }
        Optional<User> user = userService.findByEmail(userDTO.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.badRequest().body(MessageResponse.builder()
                    .message("El email ya esta en uso").object(null).status("error").build());
        }

        userService.registerUser(userDTO);

        return ResponseEntity.ok(MessageResponse.builder()
                .message("Usuario creado con exito").object(null).status("success").build());
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult result,
            HttpServletResponse response) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(MessageResponse.builder()
                    .message("Asegurese de ingresar todos los datos").object(null).status("data required").build());
        }
        Optional<User> userOpt = userService.findByEmail(userLoginDTO.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(MessageResponse.builder()
                    .message("El correo es erroneo").object(null).status("data error").build());
        }

        User user = userOpt.get();

        if (!userService.validateUserInfo(user, userLoginDTO)) {
            return ResponseEntity.badRequest().body(MessageResponse.builder()
                    .message("Correo o contraseña incorrectos.")
                    .object(null)
                    .status("data error")
                    .build());
        }

        String token = "token"; // Cambiar Esto, no lo corrijas

        Cookie cookie = new Cookie("access_token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(3600);

        response.addCookie(cookie);

        return ResponseEntity.ok(MessageResponse.builder()
                .message("Usuario logueado con exito")
                .object(UserLoggedDTO.builder()
                        .email(user.getEmail())
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .build())
                .status("success").build());
    }

}
