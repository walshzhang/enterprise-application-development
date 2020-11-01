package iteach.javaee.springdatajdbc;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("books")
public class Book {
    @Id
    private String id;
    private String title;
    private double price;

    @PersistenceConstructor
    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }
}
