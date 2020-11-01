package iteach.javaee.springdatajdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

import java.util.UUID;

@SpringBootApplication
public class SpringDataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJdbcApplication.class, args);
    }

    @Bean
    public CommandLineRunner accessDatabase(BookRepository repository) {
        return args -> {
            System.out.println(repository.findAll());
            repository.save(new Book("book1", 10));
            System.out.println(repository.findByTitleLike("book"));
        };
    }

    @Bean
    public ApplicationListener<BeforeSaveEvent<Object>> timeStampingSaveTime() {
        return event -> {
            Object entity = event.getEntity();
            if (entity instanceof Book) {
                Book book = (Book)entity;
                book.setId(UUID.randomUUID().toString());
            }
        };
    }
}
