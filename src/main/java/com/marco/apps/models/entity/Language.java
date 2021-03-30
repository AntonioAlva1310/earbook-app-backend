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
@Table(name="languages")
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String languageName;
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();
    @JsonIgnore
    @OneToMany(mappedBy="language", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<AudioBook> audiobooks;

    public Language(String languageName) {
        this.languageName = languageName;
    }
}
