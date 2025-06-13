package api_rest.app.controllers;

import api_rest.app.models.entity.User;
import api_rest.app.models.payload.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api_rest.app.services.implement.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<?> showAllUsers() {
        List<User> users = userService.listAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(
                    MessageResponse.builder()
                            .message("Users found")
                            .object(users)
                            .build());
        }
    }

}
