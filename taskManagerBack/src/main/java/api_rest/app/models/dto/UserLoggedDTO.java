package api_rest.app.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoggedDTO {
    private String firstname;
    private String lastname;
    private String email;
}
