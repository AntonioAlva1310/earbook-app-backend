package com.marco.apps.controllers;


import com.marco.apps.models.entity.Author;
import com.marco.apps.models.entity.Publisher;

import com.marco.apps.services.IPublisherService;
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
public class PublisherController  {

    @Autowired
    private IPublisherService publisherService;

    @GetMapping("/publishers")
    public List<Publisher> getpublishers(){
        return publisherService.findAll();
    }

    @GetMapping("/publishers/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Publisher publisher = null;
        Map<String, Object > response = new HashMap<>();
        try{
            publisher = publisherService.findById(id);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to query the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(publisher == null){
            response.put("message", "The publisher with id: ".concat(id.toString().concat("does not exist")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Publisher>(publisher, HttpStatus.OK);
    }






    @PostMapping("/publishers")
    public ResponseEntity<?> create(@RequestBody Publisher publisher){
        Publisher newPublisher = null;
        Map<String, Object> response = new HashMap<>();

        try{
            newPublisher = publisherService.save(publisher);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to save into database");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "The publisher was successfully created");
        response.put("publisher", newPublisher);

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }



    @PutMapping("/publishers/{id}")
    public ResponseEntity<?> update(@RequestBody Publisher publisher, @PathVariable Long id){

        Publisher currentPublisher = publisherService.findById(id);
        Publisher updatedPublisher = null;

        Map<String, Object> response = new HashMap<>();

        if(currentPublisher == null){
            response.put("message", "Error,  impossible to edit. The publisher with id: ".concat(id.toString().concat("does not exist")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            currentPublisher.setPublisherName(publisher.getPublisherName());
            updatedPublisher = publisherService.save(currentPublisher);
        }catch(DataAccessException e){
            response.put("message", "Error, failed attempt to update the publisher in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The publisher was successfully updated");
        response.put("publisher", updatedPublisher);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }




    @DeleteMapping("/publishers/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

      Map<String, Object> response = new HashMap<>();

      try{
          publisherService.delete(id);
      }catch(DataAccessException e){
          response.put("message", "Error, failed attempt to delete the publisher");
          response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
          return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }
        response.put("message", "Publisher has been successfully deleted");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
}
