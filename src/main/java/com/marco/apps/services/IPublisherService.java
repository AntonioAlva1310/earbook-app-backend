package com.marco.apps.services;

import com.marco.apps.models.entity.Language;
import com.marco.apps.models.entity.Publisher;

import java.util.List;

public interface IPublisherService {
    public List<Publisher> findAll();

    public Publisher findById(Long id);

    public Publisher save(Publisher publisher);

    public void delete(Long id);

}
