package com.marco.apps.controllers;


import com.marco.apps.models.entity.Author;
import com.marco.apps.models.entity.Language;
import com.marco.apps.models.entity.Publisher;
import com.marco.apps.services.ILanguageService;
import com.marco.apps.services.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path= "api/v1")
public class LanguageController {

    @Autowired
    private ILanguageService languageService;

    @GetMapping("/languages")
    public List<Language> getLanguages(){
        return languageService.findAll();
    }

    @GetMapping("/languages/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        Language language = null;
        Map<String, Object> response = new Hashtable<>();
        try{
            language = languageService.findById(id);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to query the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        if( language == null){
            response.put("message", "The language with id: ".concat(id.toString().concat("does not exist")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Language>(language, HttpStatus.OK);
    }

    @PostMapping("/languages")
    public ResponseEntity<?> create(@RequestBody Language language){
        Language newLanguage = null;
        Map<String, Object> response = new HashMap<>();

        try{
            newLanguage = languageService.save(language);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to save into database");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("message", "The language was successfully created");
        response.put("language", newLanguage);

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }


    @PutMapping("/languages/{id}")
    public ResponseEntity<?> update(@RequestBody Language language, @PathVariable Long id){

        Language currentLanguage = languageService.findById(id);
        Language updatedLanguage = null;

        Map<String, Object> response = new HashMap<>();

        if(currentLanguage == null){
            response.put("message", "Error,  impossible to edit. The language with id: ".concat(id.toString().concat("does not exist")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            currentLanguage.setLanguageName(language.getLanguageName());
            updatedLanguage = languageService.save(currentLanguage);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to update the language in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The language was successfully updated");
        response.put("language", updatedLanguage);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/languages/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        try{
            languageService.delete(id);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to delete the language");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Language has been successfully deleted");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }
}
