package com.alasdoo.developercourseassignment.mapper;

import com.alasdoo.developercourseassignment.dto.StudentDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.dto.TeacherDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.entity.StudentDeveloperCourse;
import com.alasdoo.developercourseassignment.entity.TeacherDeveloperCourse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherDeveloperCourseMapper {

    public TeacherDeveloperCourseDTO transformToDTO(TeacherDeveloperCourse teacherDeveloperCourseSrc) {
        TeacherDeveloperCourseDTO teacherDeveloperCourseDTO = new TeacherDeveloperCourseDTO();
        BeanUtils.copyProperties(teacherDeveloperCourseSrc, teacherDeveloperCourseDTO);
        return teacherDeveloperCourseDTO;
    }

    public TeacherDeveloperCourse transformToEntity(TeacherDeveloperCourseDTO teacherDeveloperCourseDTOSrc) {
        TeacherDeveloperCourse teacherDeveloperCourse = new TeacherDeveloperCourse();
        BeanUtils.copyProperties(teacherDeveloperCourseDTOSrc, teacherDeveloperCourse);
        return teacherDeveloperCourse;
    }

    public List<TeacherDeveloperCourseDTO> transformToListOfDTO(List<TeacherDeveloperCourse> teacherDeveloperCourseDTOSrc) {
        List<TeacherDeveloperCourseDTO> teacherDeveloperCourseDTO = new ArrayList<>(teacherDeveloperCourseDTOSrc.size());
        for (TeacherDeveloperCourse teacherDeveloperCourse : teacherDeveloperCourseDTOSrc) {
            teacherDeveloperCourseDTO.add(transformToDTO(teacherDeveloperCourse));
        }
        return teacherDeveloperCourseDTO;
    }
}
