package com.marco.apps.controllers;


import com.marco.apps.models.entity.Author;
import com.marco.apps.services.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path= "api/v1")
public class AuthorController {

    @Autowired
    private IAuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAuthors(){
        return authorService.findAll();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        Author author = null;
        Map<String, Object> response = new HashMap<>();
        try{
            author = authorService.findById(id);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to query the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        if(author == null){
            response.put("message", "The author with id: ".concat(id.toString().concat("does not exist")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @PostMapping("/authors")
    public ResponseEntity<?> create(@RequestBody Author author){
           Author newAuthor = null;
        Map<String, Object> response = new HashMap<>();

           try{
               newAuthor = authorService.save(author);
           }catch(DataAccessException e){
               response.put("message", "Error, failed attempt to save into database");
               response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
               return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
           }


           response.put("message", "The author was successfully created");
           response.put("author", newAuthor);

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<?> update(@RequestBody Author author, @PathVariable Long id){

        Author currentAuthor = authorService.findById(id);
        Author updatedAuthor = null;

        Map<String, Object> response = new HashMap<>();


        if(currentAuthor == null){
            response.put("message", "Error,  impossible to edit. The author with id: ".concat(id.toString().concat("does not exist")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            currentAuthor.setFirstName(author.getFirstName());
            currentAuthor.setLastName(author.getLastName());
            currentAuthor.setBiography(author.getBiography());
            currentAuthor.setProfilePicture(author.getProfilePicture());

            updatedAuthor =   authorService.save(currentAuthor);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to update the author in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The author was successfully updated");
        response.put("author", updatedAuthor);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();

        try{
            authorService.delete(id);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to delete the author");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Author has been successfully deleted");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

}
