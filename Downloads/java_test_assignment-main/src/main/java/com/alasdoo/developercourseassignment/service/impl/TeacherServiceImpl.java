package com.alasdoo.developercourseassignment.service.impl;

import com.alasdoo.developercourseassignment.dto.TeacherDTO;
import com.alasdoo.developercourseassignment.entity.Student;
import com.alasdoo.developercourseassignment.entity.Teacher;
import com.alasdoo.developercourseassignment.mapper.TeacherMapper;
import com.alasdoo.developercourseassignment.repository.TeacherDeveloperCourseRepository;
import com.alasdoo.developercourseassignment.repository.TeacherRepository;
import com.alasdoo.developercourseassignment.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    TeacherDeveloperCourseRepository teacherDeveloperCourseRepository;

    @Override
    public TeacherDTO findOne(Integer id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (!teacher.isPresent()) {
//            throw new IllegalArgumentException
//                    ("Teacher with the following id = " + id + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return teacherMapper.transformToDTO(teacher.get());
    }

    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository.findAll().stream().map(i -> teacherMapper.transformToDTO(i)).collect(Collectors.toList());
    }

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.transformToEntity(teacherDTO);
        return teacherMapper.transformToDTO(teacherRepository.save(teacher));
    }

    @Override
    public void remove(Integer id) throws IllegalArgumentException {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (!teacher.isPresent()) {
//            throw new IllegalArgumentException
//                    ("Teacher with the following id = " + id + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (teacherDeveloperCourseRepository.findByTeacherId(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherDTO update(Integer id, TeacherDTO teacherDTO) {
        Optional<Teacher> oldTeacher = teacherRepository.findById(id);
        if (!oldTeacher.isPresent()) {
//            throw new IllegalArgumentException
//                    ("Teacher with the following id = " + id + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        oldTeacher.get().setTeacherName(teacherDTO.getTeacherName());
        oldTeacher.get().setTeacherSurname(teacherDTO.getTeacherSurname());
        oldTeacher.get().setTeacherEmail(teacherDTO.getTeacherEmail());
        teacherRepository.save(oldTeacher.get());
        return teacherMapper.transformToDTO(oldTeacher.get());
    }

    @Override
    public TeacherDTO findByTeacherNameAndTeacherSurname(String name, String surname) {
        Optional<Teacher> teacher = teacherRepository.findByTeacherNameAndTeacherSurname(name, surname);
        if (!teacher.isPresent()) {
//            throw new IllegalArgumentException
//                    ("Teacher with the provided name and surname combination is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return teacherMapper.transformToDTO(teacher.get());
    }

    @Override
    public TeacherDTO findByTeacherEmail(String email) {
        Optional<Teacher> teacher = teacherRepository.findByTeacherEmail(email);
        if (!teacher.isPresent()) {
//            throw new IllegalArgumentException
//                    ("Teacher with this email " + email + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return teacherMapper.transformToDTO(teacher.get());
    }
}
