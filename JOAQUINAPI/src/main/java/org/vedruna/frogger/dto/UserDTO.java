package org.vedruna.frogger.dto;

import org.vedruna.frogger.persistance.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    Integer userId;
    String username;
    String email;
    String rolName;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.rolName = user.getUserRol().getRolName();
    }

    public void UserDTO2(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
