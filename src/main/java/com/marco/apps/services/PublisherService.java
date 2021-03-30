package com.marco.apps.services;

import com.marco.apps.models.entity.Publisher;
import com.marco.apps.repository.AuthorRepository;
import com.marco.apps.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherService implements IPublisherService{

    private final PublisherRepository publisherRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    @Transactional
    public void delete(Long id) {
       publisherRepository.deleteById(id);
    }
}
