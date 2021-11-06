package com.alasdoo.developercourseassignment;

import com.alasdoo.developercourseassignment.entity.StudentDeveloperCourse;
import com.alasdoo.developercourseassignment.repository.StudentDeveloperCourseRepository;
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
public class StudentDeveloperCourseTest {

    @Autowired
    StudentDeveloperCourseRepository studentDeveloperCourseRepository;
    StudentDeveloperCourse studentDeveloperCourse = new StudentDeveloperCourse(15, 26, 6);

    @Test
    @Order(2)
    public void testGetStudentDeveloperCourse(){
        Integer id = 1;
        Optional<StudentDeveloperCourse> studentDeveloperCourse = studentDeveloperCourseRepository.findById(id);
        System.out.println(studentDeveloperCourse);

        assertThat(studentDeveloperCourse.get().getId().equals(id));
    }

    @Test
    @Order(3)
    public void testGetListOfStudentDeveloperCourses(){
        List<StudentDeveloperCourse> studentDeveloperCourses = studentDeveloperCourseRepository.findAll();
        for (StudentDeveloperCourse studentDeveloperCourse: studentDeveloperCourses){
            System.out.println(studentDeveloperCourse);
        }

        assertThat(studentDeveloperCourses).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(1)
    public void testCreateStudentDeveloperCourse(){

        StudentDeveloperCourse savedStudentDeveloperCourse = studentDeveloperCourseRepository.save(studentDeveloperCourse);

        assertNotNull(savedStudentDeveloperCourse);
    }

    @Test
    @Rollback(false)
    @Order(8)
    public void testDeleteStudentDeveloperCourse(){
        //change id for student developer course
        Integer id = 8;
        boolean isExistsBeforeDelete = studentDeveloperCourseRepository.findById(id).isPresent();
        studentDeveloperCourseRepository.deleteById(id);
        boolean notExistsAfterDelete = studentDeveloperCourseRepository.findById(id).isPresent();

        assertTrue(isExistsBeforeDelete);
        assertFalse(notExistsAfterDelete);
    }

    @Test
    @Rollback(false)
    @Order(4)
    public void testUpdateStudentDeveloperCourse(){
        //change id for student developer course
        Integer id = 8;
        Integer classesBoth = 7;

        studentDeveloperCourse.setClassesBought(classesBoth);
        studentDeveloperCourse.setId(id);
        studentDeveloperCourseRepository.save(studentDeveloperCourse);
        assertThat(studentDeveloperCourse);
    }

    @Test
    @Order(5)
    public void testFindStudentDeveloperCourseByStudentId(){
        Integer studentId = 1;
        Optional<List<StudentDeveloperCourse>> studentDeveloperCourses = studentDeveloperCourseRepository.findByStudentId(studentId);
        System.out.println(studentDeveloperCourses);

        assertThat(studentDeveloperCourses);
    }

    @Test
    @Order(7)
    public void testFindStudentDeveloperCourseByCourseId(){
        Integer courseId = 6;
        Optional<List<StudentDeveloperCourse>> studentDeveloperCourses = studentDeveloperCourseRepository.findByDeveloperCourseId(courseId);
        System.out.println(studentDeveloperCourses);

        assertThat(studentDeveloperCourses);
    }

    @Test
    @Order(6)
    public void testFindStudentByByDeveloperCourseIdAndStudentId(){
        Integer studentId = 1;
        Integer courseId = 6;
        Optional<StudentDeveloperCourse> studentDeveloperCourse = studentDeveloperCourseRepository.findByDeveloperCourseIdAndStudentId(courseId,studentId);
        System.out.println(studentDeveloperCourse);

        assertThat(studentDeveloperCourse.get().getDeveloperCourseId().equals(courseId) && studentDeveloperCourse.get().getStudentId().equals(studentId));
    }

}

