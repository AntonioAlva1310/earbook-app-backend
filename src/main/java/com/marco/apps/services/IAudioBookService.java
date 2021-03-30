package com.marco.apps.services;

import com.marco.apps.models.entity.AudioBook;



import java.util.List;

public interface IAudioBookService {

    public List<AudioBook> findAll();

    public AudioBook findById(Long id);

    public AudioBook save(AudioBook audiobook);

    public void delete(Long id);
}
