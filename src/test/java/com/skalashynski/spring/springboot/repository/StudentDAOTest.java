package com.skalashynski.spring.springboot.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class StudentDAOTest {
    /*

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    */
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void injectedComponentsAreNotNUll() {
        /*

        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(entityManager);

        */
        assertNotNull(studentRepository);
    }

    @Test
    void betweenDates() {
        assertEquals(3, studentRepository.findBetweenBirthdays(Date.valueOf("1900-03-17"), Date.valueOf("2000-03-17")).size());
    }
}