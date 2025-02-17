package org.vedruna.frogger.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.vedruna.frogger.dto.UserDTO;
import org.vedruna.frogger.persistance.model.User;
import org.vedruna.frogger.persistance.repository.UserRepositoryI;

@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepositoryI userRepository;

    UserDTO userDTO = new UserDTO();

    public UserDTO selectMyUser(User user) {
        Optional<User> u = userRepository.findById(user.getUserId());
        if (u.isPresent()) {
            return new UserDTO(u.get());
        } else {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @Override
    public Page<UserDTO> findUsersByNameStartingWith(String name, Pageable pageable) {
        return userRepository.findByUsernameStartingWith(name,pageable)
                .map(user -> new UserDTO());
    }

    @Override
    public boolean deleteUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true; // Usuario eliminado correctamente
        } else {
            return false; // Usuario no encontrado
        }
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        return userDTO;
    }

}
