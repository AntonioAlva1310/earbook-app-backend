package com.marco.apps.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode

//@Table(
//        name="authors",
//        uniqueConstraints=
//        @UniqueConstraint(columnNames={"first_name", "last_name"})
//)
@Table(name="authors")
@Entity

public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String firstName;
    private String LastName;
    private String Biography;
    private String profilePicture;
    @JsonIgnore
    @OneToMany(mappedBy="author", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<AudioBook>  audiobooks;
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();


    public Author(){


    }
    public Author(String firstName, String lastName, String biography, String profilePicture) {
        this.firstName = firstName;
        LastName = lastName;
        Biography = biography;
        this.profilePicture = profilePicture;

    }
}
