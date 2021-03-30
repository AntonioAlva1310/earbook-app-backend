package com.marco.apps.services;

import com.marco.apps.models.entity.AudioBook;
import com.marco.apps.models.entity.Author;

import java.util.List;

public interface IAuthorService  {

    public List<Author> findAll();

    public Author findById(Long id);

    public Author save(Author author);

    public void delete(Long id);
}
