package project.community.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserCreateDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
}
