package com.marco.apps.controllers;




import com.marco.apps.models.entity.AudioBook;
import com.marco.apps.models.entity.Author;
import com.marco.apps.models.entity.Language;
import com.marco.apps.models.entity.Publisher;
import com.marco.apps.services.AudioBookService;
import com.marco.apps.services.IAudioBookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path= "api/v1")
public class
AudioBookController {
    private final IAudioBookService audioBookService;

    @Autowired
    public AudioBookController(AudioBookService audioBookService) {
        this.audioBookService = audioBookService;
    }

    @GetMapping("/audiobooks")
    public List<AudioBook> getAudiobooks() {
        return audioBookService.findAll();
    }

    @GetMapping("/audiobooks/{id}")
    public AudioBook show(@PathVariable Long id){
        return audioBookService.findById(id);
    }

    @PostMapping("/audiobooks")
    @ResponseStatus(HttpStatus.CREATED)
    public AudioBook create(@RequestBody AudioBook audioBook){
        return audioBookService.save(audioBook);
    }


    @PutMapping("/audiobook/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AudioBook update(@RequestBody AudioBook audioBook, @PathVariable Long id){
        AudioBook currentAudioBook = audioBookService.findById(id);

        currentAudioBook.setTitle(audioBook.getTitle());
        currentAudioBook.setCover(audioBook.getCover());
        currentAudioBook.setAuthor(audioBook.getAuthor());
        currentAudioBook.setPublisher(audioBook.getPublisher());
        currentAudioBook.setLanguage(audioBook.getLanguage());

        return audioBookService.save(currentAudioBook);
    }



    @DeleteMapping("/audiobooks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        audioBookService.delete(id);
    }

}
