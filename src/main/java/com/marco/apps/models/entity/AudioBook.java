package com.marco.apps.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="audio_books")
public class AudioBook implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String title;
    private String cover;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private Author author;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="publisher_id", referencedColumnName = "id")
    private Publisher publisher;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="language_id", referencedColumnName = "id")
    private Language language;
    @Temporal(TemporalType.DATE)
    private Date releaseDate = new Date();
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();



    public AudioBook(String title, String cover, Author author, Publisher publisher, Language language) {
        this.title = title;
        this.cover = cover;
        this.author = author;
        this.publisher = publisher;
        this.language = language;


    }
}
