package edu.fscj.cen3024c.taskmanager.services;

import edu.fscj.cen3024c.taskmanager.dto.UserDTO;
import edu.fscj.cen3024c.taskmanager.entities.User;
import edu.fscj.cen3024c.taskmanager.exceptions.UserNotFoundException;
import edu.fscj.cen3024c.taskmanager.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return convertToDTO(user);
    }

    public User findByIdEntity(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public UserDTO updateUser(Integer id, User userDetails) {
        User existingUser = findByIdEntity(id);

        existingUser.setUsername(userDetails.getUsername());
        existingUser.setPassword(userDetails.getPassword());

        User updatedUser = userRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }

    public void deleteById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }

    public UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername()
        );
    }
}