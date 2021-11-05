package com.alasdoo.developercourseassignment.service;

import com.alasdoo.developercourseassignment.dto.TeacherDTO;

public interface TeacherService extends CrudService<TeacherDTO>{

    TeacherDTO findByTeacherNameAndTeacherSurname(String name, String surname);

    TeacherDTO findByTeacherEmail(String email);
}
