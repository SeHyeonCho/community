package project.community.dto.user;

import lombok.Getter;
import project.community.domain.User;
import project.community.domain.UserRole;

@Getter
public class UserResponse {

    private String username;
    private UserRole userRole;
    private String token;

    public UserResponse(User user,String token) {
        this.username = user.getName();
        this.userRole = user.getUserRole();
        this.token = token;
    }

}
