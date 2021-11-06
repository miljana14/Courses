package com.alasdoo.developercourseassignment;

import com.alasdoo.developercourseassignment.entity.Teacher;
import com.alasdoo.developercourseassignment.repository.TeacherRepository;
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
public class TeacherTest {

    @Autowired
    TeacherRepository teacherRepository;

    Teacher teacher = new Teacher("Ma", "An", "markoan@gmail.com");

    @Test
    @Order(2)
    public void testGetTeacher(){
        Integer id = 3;
        Optional<Teacher> teacher = teacherRepository.findById(id);
        System.out.println(teacher);

        assertThat(teacher.get().getId().equals(id));
    }

    @Test
    @Order(3)
    public void testGetListOfTeachers(){
        List<Teacher> teachers = teacherRepository.findAll();
        for (Teacher teacher: teachers){
            System.out.println(teacher);
        }

        assertThat(teachers).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(1)
    public void testCreateTeacher(){

        Teacher savedTeacher = teacherRepository.save(teacher);

        assertNotNull(savedTeacher);
    }

    @Test
    @Rollback(false)
    @Order(7)
    public void testDeleteTeacher(){
        //change id for teacher
        Integer id = 76;
        boolean isExistsBeforeDelete = teacherRepository.findById(id).isPresent();
        teacherRepository.deleteById(id);
        boolean notExistsAfterDelete = teacherRepository.findById(id).isPresent();

        assertTrue(isExistsBeforeDelete);
        assertFalse(notExistsAfterDelete);
    }

    @Test
    @Rollback(false)
    @Order(4)
    public void testUpdateTeacher(){
        //change id for teacher
        Integer id = 76;
        String name = "Marko";

        teacher.setTeacherName(name);
        teacher.setId(id);
        teacherRepository.save(teacher);
        assertThat(teacher);
    }

    @Test
    @Order(5)
    public void testFindTeacherByEmail(){
        String email = "hendrerit@pretiumet.com";
        Optional<Teacher> teacher = teacherRepository.findByTeacherEmail(email);
        System.out.println(teacher);

        assertThat(teacher.get().getTeacherEmail().equals(email));
    }

    @Test
    @Order(6)
    public void testFindTeacherByNameAndSurname(){
        String name = "Grady";
        String surname = "Cantu";
        Optional<Teacher> teacher = teacherRepository.findByTeacherNameAndTeacherSurname(name,surname);
        System.out.println(teacher);

        assertThat(teacher.get().getTeacherName().equals(name) && teacher.get().getTeacherSurname().equals(surname));
    }

}

