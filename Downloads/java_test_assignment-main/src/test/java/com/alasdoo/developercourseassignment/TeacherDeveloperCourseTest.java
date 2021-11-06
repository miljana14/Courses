package com.alasdoo.developercourseassignment;

import com.alasdoo.developercourseassignment.entity.TeacherDeveloperCourse;
import com.alasdoo.developercourseassignment.repository.TeacherDeveloperCourseRepository;
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
public class TeacherDeveloperCourseTest {

    @Autowired
    TeacherDeveloperCourseRepository teacherDeveloperCourseRepository;
    TeacherDeveloperCourse teacherDeveloperCourse = new TeacherDeveloperCourse(15,12);

    @Test
    @Order(2)
    public void testGetTeacherDeveloperCourse(){
        Integer id = 1;
        Optional<TeacherDeveloperCourse> teacherDeveloperCourse = teacherDeveloperCourseRepository.findById(id);
        System.out.println(teacherDeveloperCourse);

        assertThat(teacherDeveloperCourse.get().getId().equals(id));
    }

    @Test
    @Order(3)
    public void testGetListOfTeacherDeveloperCourses(){
        List<TeacherDeveloperCourse> teacherDeveloperCourses = teacherDeveloperCourseRepository.findAll();
        for (TeacherDeveloperCourse teacherDeveloperCourse: teacherDeveloperCourses){
            System.out.println(teacherDeveloperCourse);
        }

        assertThat(teacherDeveloperCourses).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(1)
    public void testCreateTeacherDeveloperCourse(){

        TeacherDeveloperCourse savedTeacherDeveloperCourse = teacherDeveloperCourseRepository.save(teacherDeveloperCourse);

        assertNotNull(savedTeacherDeveloperCourse);
    }

    @Test
    @Rollback(false)
    @Order(7)
    public void testDeleteTeacherDeveloperCourse(){
        //change id for teacher developer course
        Integer id = 118;
        boolean isExistsBeforeDelete = teacherDeveloperCourseRepository.findById(id).isPresent();
        teacherDeveloperCourseRepository.deleteById(id);
        boolean notExistsAfterDelete = teacherDeveloperCourseRepository.findById(id).isPresent();

        assertTrue(isExistsBeforeDelete);
        assertFalse(notExistsAfterDelete);
    }

    @Test
    @Rollback(false)
    @Order(4)
    public void testUpdateTeacherDeveloperCourse(){
        //change id for teacher developer course
        Integer id = 118;
        Integer teacherId = 7;

        teacherDeveloperCourse.setTeacherId(teacherId);
        teacherDeveloperCourse.setId(id);
        teacherDeveloperCourseRepository.save(teacherDeveloperCourse);
        assertThat(teacherDeveloperCourse);
    }

    @Test
    @Order(5)
    public void testFindTeacherDeveloperCourseByTeacherId(){
        Integer teacherId = 10;
        Optional<List<TeacherDeveloperCourse>> teacherDeveloperCourses = teacherDeveloperCourseRepository.findByTeacherId(teacherId);
        System.out.println(teacherDeveloperCourses);

        assertThat(teacherDeveloperCourses);
    }

    @Test
    @Order(6)
    public void testFindTeacherDeveloperCourseByDeveloperCourseIdAndTeacherId(){
        Integer teacherId = 10;
        Integer courseId = 14;
        Optional<TeacherDeveloperCourse> teacherDeveloperCourse = teacherDeveloperCourseRepository.findByDeveloperCourseIdAndTeacherId(courseId,teacherId);
        System.out.println(teacherDeveloperCourse);

        assertThat(teacherDeveloperCourse.get().getDeveloperCourseId().equals(courseId) && teacherDeveloperCourse.get().getTeacherId().equals(teacherId));
    }

}


