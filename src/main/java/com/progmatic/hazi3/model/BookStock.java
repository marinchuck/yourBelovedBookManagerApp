package com.progmatic.hazi3.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookStock {

    @EmbeddedId
    BookStockKey id;

    @ManyToOne
    @MapsId("storeId")
//    @JoinColumn(name = "store_id")
    Store store;

    @ManyToOne
    @MapsId("bookId")
//    @JoinColumn(name = "book_id")
    Book book;

    int pcs;

    public BookStock(Store store, Book book, int pcs) {
        this.store = store;
        this.book = book;
        this.pcs = pcs;
    }
}
