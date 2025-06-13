package api_rest.app.models.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDTO implements Serializable {

    @Email(message = "Email is invalid")
    @NotBlank(message = "Email is required")
    private String email;
    @Size(min = 8, message = "Password needs at least 8 characters")
    private String password;
}
