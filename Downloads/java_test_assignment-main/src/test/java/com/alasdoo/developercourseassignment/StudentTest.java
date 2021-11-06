package com.alasdoo.developercourseassignment;

import com.alasdoo.developercourseassignment.entity.Student;
import com.alasdoo.developercourseassignment.repository.StudentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTest {

    @Autowired
    StudentRepository studentRepository;
    Student student = new Student("Marko", "Andric", "markoA", "andricM", "markoandric@gmail.com", 2666);

    @Test
    @Order(2)
    public void testGetStudent(){
        Integer id = 1;
        Optional<Student> student = studentRepository.findById(id);
        System.out.println(student);

        assertThat(student.get().getId().equals(id));
    }

    @Test
    @Order(3)
    public void testGetListOfStudents(){
        List<Student> students = studentRepository.findAll();
        for (Student student: students){
            System.out.println(student);
        }

        assertThat(students).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(1)
    public void testCreateStudent(){

        Student savedStudent = studentRepository.save(student);

        assertNotNull(savedStudent);
    }

    @Test
    @Rollback(false)
    @Order(7)
    public void testDeleteStudent(){
        //change id for student
        Integer id = 109;
        boolean isExistsBeforeDelete = studentRepository.findById(id).isPresent();
        studentRepository.deleteById(id);
        boolean notExistsAfterDelete = studentRepository.findById(id).isPresent();

        assertTrue(isExistsBeforeDelete);
        assertFalse(notExistsAfterDelete);
    }

    @Test
    @Rollback(false)
    @Order(4)
    public void testUpdateStudent(){
        //change id for student
        Integer id = 109;
        String password = "mmmrrr";

        student.setPassword(password);
        student.setId(id);
        studentRepository.save(student);
        assertThat(student);
    }

    @Test
    @Order(5)
    public void testFindStudentByAccountName(){
        String accountName = "miljana";
        Optional<Student> student = studentRepository.findByAccountName(accountName);
        System.out.println(student);

        assertThat(student.get().getAccountName().equals(accountName));
    }

    @Test
    @Order(6)
    public void testFindStudentByAccountNameAndPassword(){
        String accountName = "miljana";
        String password = "mmm";
        Optional<Student> student = studentRepository.findByAccountNameAndPassword(accountName,password);
        System.out.println(student);

        assertThat(student.get().getAccountName().equals(accountName) && student.get().getPassword().equals(password));
    }

}
