package org.vedruna.frogger.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.vedruna.frogger.dto.UserDTO;
import org.vedruna.frogger.persistance.model.User;

public interface UserServiceI {
    UserDTO selectMyUser(User user);

    Page<UserDTO> findUsersByNameStartingWith(String name, Pageable pageable);

    boolean deleteUser(Integer userId);

    UserDTO getUserById(Integer userId);

    boolean unfollowUser(Integer userId, Integer userId1);

    Page<UserDTO> getFollowingUsers(Integer userId, Pageable pageable);

    Page<UserDTO> getFollowers(Integer userId, Pageable pageable);
}