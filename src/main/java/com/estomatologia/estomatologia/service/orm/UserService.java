package com.estomatologia.estomatologia.service.orm;

import com.estomatologia.estomatologia.model.User;
import com.estomatologia.estomatologia.repository.UserRepository;
import com.estomatologia.estomatologia.service.orm.CrudService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserService implements CrudService<User, Long> {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> findAll() {
        Set<User> credentials = new HashSet<>();
        userRepository.findAll().forEach(credentials::add);
        return credentials;
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}
