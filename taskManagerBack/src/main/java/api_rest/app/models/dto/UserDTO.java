package api_rest.app.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

import java.util.UUID;

@Data
@Builder
public class UserDTO implements Serializable {
    private UUID id;
    @NotBlank(message = "Firstname is required")
    private String firstname;
    @NotBlank(message = "Lastname is required")
    private String lastname;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password needs at least 8 characters")
    private String password;
    @Email(message = "Email is invalid")
    @NotBlank(message = "Email is required")
    private String email;

}