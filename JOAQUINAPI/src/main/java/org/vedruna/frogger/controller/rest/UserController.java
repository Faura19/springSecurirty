package org.vedruna.frogger.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import org.vedruna.frogger.dto.UserDTO;
import org.vedruna.frogger.persistance.model.User;
import org.vedruna.frogger.service.UserServiceI;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceI userService;


    // 3 End point que devuelve los usuarios con un get

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
            userService.selectMyUser(user)
        );
    }

    // 4 End point con los usuarios con dicho nombre

    @GetMapping("/search")
    public ResponseEntity<Page<UserDTO>> searchUsersByName(
            @RequestParam String name,
            Pageable pageable) {

        Page<UserDTO> users = userService.findUsersByNameStartingWith(name, pageable);
        return ResponseEntity.ok(users);
    }

    // 6 End point que devuelve el usuario por el id

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        UserDTO user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    // 7 Delete mediante id

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        boolean deleted = userService.deleteUser(userId);
        if (deleted) {
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
    }

    // Endpoint para dejar de seguir a un usuario el cual no es obligatorio
    @DeleteMapping("/unfollow/{userId}")
    public ResponseEntity<String> unfollowUser(
            @AuthenticationPrincipal User authenticatedUser,
            @PathVariable Integer userId) {
        boolean unfollowed = userService.unfollowUser(authenticatedUser.getUserId(), userId);
        if (unfollowed) {
            return ResponseEntity.ok("Has dejado de seguir al usuario con ID: " + userId);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo dejar de seguir al usuario.");
        }
    }

    // Obtener usuarios seguidos de un usuario con paginación que no es obligatorio
    @GetMapping("/follow/{userId}")
    public ResponseEntity<Page<UserDTO>> getFollowingUsers(
            @PathVariable Integer userId,
            Pageable pageable) {
        Page<UserDTO> following = userService.getFollowingUsers(userId, pageable);
        return ResponseEntity.ok(following);
    }

    // Obtener seguidores de un usuario con paginación que no es obligatorio
    @GetMapping("/followers/{userId}")
    public ResponseEntity<Page<UserDTO>> getFollowers(
            @PathVariable Integer userId,
            Pageable pageable) {
        Page<UserDTO> followers = userService.getFollowers(userId, pageable);
        return ResponseEntity.ok(followers);
    }

}

/*
    @Autowired
    private ScoreServiceI scoreService;

    @PostMapping
    public ResponseEntity<Score> insertScore(@AuthenticationPrincipal User user, @RequestBody Score score) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Score newScore = scoreService.insertScore(user, score);
        return ResponseEntity.status(HttpStatus.CREATED).body(newScore);
    }*/
