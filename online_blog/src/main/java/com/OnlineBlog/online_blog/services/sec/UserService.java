package com.OnlineBlog.online_blog.services.sec;

import com.OnlineBlog.online_blog.models.sec.UserSec;
import com.OnlineBlog.online_blog.repositories.sec.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository repository;

    @Override
    public List<UserSec> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserSec> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public UserSec save(UserSec userSec) {
        return repository.save(userSec);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(UserSec userSec) {
        this.save(userSec);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
