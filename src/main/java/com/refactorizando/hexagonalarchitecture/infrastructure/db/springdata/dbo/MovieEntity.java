package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="movie")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String image;

    private LocalDateTime creationDate;

    private Integer rating;

    @ManyToMany(mappedBy = "movies")
    private Set<CharacterEntity> charters = new HashSet<>();

    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany
    private Set<GenreEntity> genres = new HashSet<>();
}

