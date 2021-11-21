package com.example.springdata;

import com.example.springdata.dao.IBookDAO;
import com.example.springdata.model.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class SpringDataApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger((Book.class));
    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @PostConstruct
    private void initDb(){
        String sqlStatement[] = {
               "INSERT INTO book (title, author,text) VALUES('Siddhartha', 'Hermann Hesse', 'Siddhartha est un roman philosophique de Hermann Hesse paru en 1922')",
                "INSERT INTO book (title, author, text) VALUES('Le Loup des steppes', 'Hermann Hesse', 'Le Loup des steppes raconte l’histoire de Harry Haller5, homme désabusé, qui se déclare tiraillé entre deux personnalités.')"

        };
        Arrays.asList(sqlStatement).forEach(sql ->{
            jdbcTemplate.execute(sql);
        });

        jdbcTemplate.query("select * from book",
                new RowMapper<Object>() {
            @Override
                    public Object mapRow(ResultSet book, int i) throws SQLException {
                System.out.println("id:" + book.getString("id")+
                        "title: " + book.getString("title") +
                        "author: " + book.getString("author")
                        );
                return null;
            }
                }
        );
    }
    /*
    @Bean
    public CommandLineRunner demo(IBookDAO dao){
        return (args)->{
            dao.save(new Book("Siddhartha", "Hermann Hesse", "Siddhartha est un roman philosophique de Hermann Hesse paru en 1922 en langue allemande. Il met en scène la quête spirituelle d'un personnage du nom de Siddhartha, " +
                    "qu'on ne confondra pas avec Siddhartha Gautama, le Bouddha historique"));
            dao.save(new Book("Le Loup des steppes", "Hermann Hesse", "Le Loup des steppes raconte l’histoire de Harry Haller5, homme désabusé, qui se déclare tiraillé entre deux personnalités."));
            dao.save( new Book("Ainsi parlait Zarathoustra", "Nietzsche","Ainsi parlait Zarathoustra est une oeuvre philosophique magistrale qui a bouleversé la pensée de l'Occident"));
            dao.save( new Book("Voyage au bout de la nuit","Celine", "Oh ! Vous etes donc tout a fait lache, Ferdinand ! Vous etes repugnant comme rat ..."));

            for(Book b : dao.findAll()){
                log.info(b.toString());
            }
            log.info("author Hermann Hess");
            for(Book b : dao.findAllByAuthor("Hermann Hesse")){
                log.info(b.toString());
            }
        };
    }*/
}

