package com.progmatic.hazi3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @NonNull
    String name;

    @Column(nullable = false, name = "birth_date")
    @NonNull
    LocalDate birthDate;

    @OneToMany(mappedBy = "author")
    List<Book> books;


    public Author(@NonNull String name, @NonNull LocalDate bd, List<Book> books) {
        this.birthDate = bd;
        this.name = name;
        this.books = books;
    }
}
