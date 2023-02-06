package com.progmatic.hazi3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @NonNull
    private Author author;

    @Column(unique = true, nullable = false)
    @NonNull
    private String isbn;

    @Column(nullable = false)
    @NonNull
    private boolean isActive;

    @OneToMany(mappedBy = "book")
    private List<BookStock> stores;

//    public Book(@NonNull String title, @NonNull Author author, @NonNull String isbn,
//        List<Store> stores) {
//        this.title = title;
//        this.author = author;
//        this.isbn = isbn;
//        this.stores = stores;
//    }
}
