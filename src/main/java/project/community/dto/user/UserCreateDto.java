package project.community.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserCreateDto {

    @NotEmpty
    private String name;

    @Length(min = 4)
    private String password;
    @NotEmpty(message = "이메일은 필수 입니다.")
    private String email;
}
