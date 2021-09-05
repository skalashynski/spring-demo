package com.skalashynski.spring.springboot.repository;

import com.skalashynski.spring.springboot.bean.Student;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void before() throws SQLException {
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("data.sql"));
    }

    @Test
    @Disabled("Not necessary test Spring Boot provided methods.")
    void actualSize() {
        int actual = studentRepository.findAll().size();
        MatcherAssert.assertThat(actual, Matchers.is(3));
    }

    @Test
    public void findBetweenBirthdays() {
        List<Student> list = studentRepository.findBetweenBirthdays(java.sql.Date.valueOf("1900-03-17"), java.sql.Date.valueOf("2030-03-17"));
        MatcherAssert.assertThat(list.size(), Matchers.is(3));
    }

    @Test
    public void findByLastName() {
        List<Student> students = studentRepository.findByLastName("Dangote");
        MatcherAssert.assertThat(students.size(), Matchers.is(1));
    }

    @Test
    public void findByFirstName() {
        List<Student> students = studentRepository.findByFirstName("Bill");
        MatcherAssert.assertThat(students.size(), Matchers.is(1));
    }
}