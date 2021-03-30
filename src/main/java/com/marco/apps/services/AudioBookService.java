package com.marco.apps.services;


import com.marco.apps.models.entity.AudioBook;

import com.marco.apps.repository.AudioBookRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AudioBookService implements IAudioBookService{

    private final AudioBookRepository audioBookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AudioBook> findAll() { return audioBookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AudioBook findById(Long id) {
        return audioBookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public AudioBook save(AudioBook audiobook) {
        return audioBookRepository.save(audiobook);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        audioBookRepository.deleteById(id);
    }
}
