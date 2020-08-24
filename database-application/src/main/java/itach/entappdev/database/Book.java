package itach.entappdev.database;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("books")
public class Book {
    @Id
    private String id;
    private String title;
    private double price;
}
