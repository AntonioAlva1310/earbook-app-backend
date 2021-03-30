package com.marco.apps.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="publishers")
public class Publisher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String publisherName;
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();
    @JsonIgnore
    @OneToMany(mappedBy="publisher", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<AudioBook> audiobooks;

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }
}
