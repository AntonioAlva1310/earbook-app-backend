package com.marco.apps.services;

import com.marco.apps.models.entity.User;

import java.util.List;

public interface IUserService {
    public List<User> findAll();

    public User findById(Long id);

    public User save(User user);

    public void delete(Long id);


}
