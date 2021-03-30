package com.marco.apps.services;

import com.marco.apps.models.entity.AudioBook;
import com.marco.apps.models.entity.Language;

import java.util.List;

public interface ILanguageService {

    public List<Language> findAll();

    public Language findById(Long id);

    public Language save(Language language);

    public void delete(Long id);
}
