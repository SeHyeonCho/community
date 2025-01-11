package project.community.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserListDto {

    private List<UserDto> userList;

    public UserListDto(List<UserDto> userList) {
        this.userList = userList;
    }
}
