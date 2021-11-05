package com.alasdoo.developercourseassignment.service;

import com.alasdoo.developercourseassignment.dto.TeacherDeveloperCourseDTO;

import java.util.List;

public interface TeacherDeveloperCourseService extends CrudService<TeacherDeveloperCourseDTO> {

    TeacherDeveloperCourseDTO findByDeveloperCourseIdAndTeacherId(Integer developerCourseId, Integer teacherId);

    List<TeacherDeveloperCourseDTO> findByTeacherId(Integer teacherId);
}
