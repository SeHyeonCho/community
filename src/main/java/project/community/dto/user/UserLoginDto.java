package project.community.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotNull
    private String username;
    @NotEmpty
    private String password;
}
