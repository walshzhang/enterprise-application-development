package itach.entappdev.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import java.util.Objects;
import java.util.UUID;

@SpringBootApplication
public class DatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(BookRepository repository) {
        Book book1 = new Book();
        book1.setTitle("book1");
        book1.setPrice(10);
        repository.save(book1);
        return args -> repository.findAll().forEach(System.out::println);
    }

    @Bean
    public ApplicationListener<BeforeSaveEvent<Book>> loggingSaves() {
        return event -> {
            Objects.requireNonNull(event.getEntity()).setId(UUID.randomUUID().toString());
        };
    }
}
