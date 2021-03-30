package com.marco.apps.services;


import com.marco.apps.models.entity.Language;
import com.marco.apps.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LanguageService implements ILanguageService {

    private final LanguageRepository languageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Language findById(Long id) {
        return languageRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Language save(Language language) {
        return languageRepository.save(language);
    }

    @Override
    @Transactional
    public void delete(Long id) {
       languageRepository.deleteById(id);
    }
}
