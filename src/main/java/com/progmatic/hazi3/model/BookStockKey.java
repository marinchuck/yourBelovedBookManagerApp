package com.progmatic.hazi3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class BookStockKey implements Serializable {

    @Column(name = "book_id")
    Long bookId;

    @Column(name = "store_id")
    Long storeId;

    public BookStockKey(Long bookId, Long storeId) {
        this.bookId = bookId;
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BookStockKey that = (BookStockKey) o;
        return Objects.equals(bookId, that.bookId) &&
            Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, storeId);
    }
}
