package com.alasdoo.developercourseassignment;

import com.alasdoo.developercourseassignment.entity.DeveloperCourse;
import com.alasdoo.developercourseassignment.repository.DeveloperCourseRepository;
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
public class DeveloperCourseTest {

    @Autowired
    DeveloperCourseRepository developerCourseRepository;
    DeveloperCourse developerCourse = new DeveloperCourse("HTML", 32, 3);

    @Test
    @Order(2)
    public void testGetDeveloperCourse(){
        Integer id = 1;
        Optional<DeveloperCourse> developerCourse = developerCourseRepository.findById(id);
        System.out.println(developerCourse);

        assertThat(developerCourse.get().getId().equals(id));
    }

    @Test
    @Order(3)
    public void testGetListOfDeveloperCourses(){
        List<DeveloperCourse> developerCourses = developerCourseRepository.findAll();
        for (DeveloperCourse developerCourse: developerCourses){
            System.out.println(developerCourse);
        }

        assertThat(developerCourses).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(1)
    public void testCreateDeveloperCourse(){

        DeveloperCourse savedDeveloperCourse = developerCourseRepository.save(developerCourse);

        assertNotNull(savedDeveloperCourse);
    }

    @Test
    @Rollback(false)
    @Order(8)
    public void testDeleteDeveloperCourse(){
        //change id for developer course
        Integer id = 48;
        boolean isExistsBeforeDelete = developerCourseRepository.findById(id).isPresent();
        developerCourseRepository.deleteById(id);
        boolean notExistsAfterDelete = developerCourseRepository.findById(id).isPresent();

        assertTrue(isExistsBeforeDelete);
        assertFalse(notExistsAfterDelete);
    }

    @Test
    @Rollback(false)
    @Order(4)
    public void testUpdateDeveloperCourse(){
        //change id for developer course
        Integer id = 48;
        Integer costPerClass = 35;

        developerCourse.setCostPerClass(costPerClass);
        developerCourse.setId(id);
        developerCourseRepository.save(developerCourse);
        assertThat(developerCourse);
    }

    @Test
    @Order(5)
    public void testFindByDeveloperCourseName(){
        String developerCourseName = "Java";
        Optional<List<DeveloperCourse>> developerCourses = developerCourseRepository.findByDeveloperCourseName(developerCourseName);
        System.out.println(developerCourses);

        assertThat(developerCourses);
    }

    @Test
    @Order(6)
    public void testFindDeveloperCourseByStudentId(){
        Integer studentId = 1;
        Optional<List<DeveloperCourse>> developerCourses = developerCourseRepository.findDevCourseByStudentId(studentId);
        System.out.println(developerCourses);

        assertThat(developerCourses);
    }

    @Test
    @Order(7)
    public void testFindDeveloperCourseByTeacherId(){
        Integer teacherId = 5;
        Optional<List<DeveloperCourse>> developerCourses = developerCourseRepository.findDevCourseByTeacherId(teacherId);
        System.out.println(developerCourses);

        assertThat(developerCourses);
    }

}

