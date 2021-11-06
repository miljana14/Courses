package com.alasdoo.developercourseassignment.service.impl;

import com.alasdoo.developercourseassignment.dto.StudentDeveloperCourseDTO;
import com.alasdoo.developercourseassignment.entity.DeveloperCourse;
import com.alasdoo.developercourseassignment.entity.Student;
import com.alasdoo.developercourseassignment.entity.StudentDeveloperCourse;
import com.alasdoo.developercourseassignment.mapper.StudentDeveloperCourseMapper;
import com.alasdoo.developercourseassignment.repository.DeveloperCourseRepository;
import com.alasdoo.developercourseassignment.repository.StudentDeveloperCourseRepository;
import com.alasdoo.developercourseassignment.repository.StudentRepository;
import com.alasdoo.developercourseassignment.service.StudentDeveloperCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentDeveloperCourseServiceImpl implements StudentDeveloperCourseService {

    @Autowired
    private StudentDeveloperCourseRepository studentDeveloperCourseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DeveloperCourseRepository developerCourseRepository;

    @Autowired
    private StudentDeveloperCourseMapper studentDeveloperCourseMapper;

    @Override
    public StudentDeveloperCourseDTO findOne(Integer id) {
        Optional<StudentDeveloperCourse> studentDeveloperCourse = studentDeveloperCourseRepository.findById(id);
        if (!studentDeveloperCourse.isPresent()) {
//            throw new IllegalArgumentException
//                ("Student course with the following id = " + id + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return studentDeveloperCourseMapper.transformToDTO(studentDeveloperCourse.get());
    }

    @Override
    public List<StudentDeveloperCourseDTO> findAll() {
        return studentDeveloperCourseRepository.findAll().stream().map(i -> studentDeveloperCourseMapper.transformToDTO(i)).collect(Collectors.toList());
    }

    @Override
    public StudentDeveloperCourseDTO save(StudentDeveloperCourseDTO studentDeveloperCourseDTO) {
        StudentDeveloperCourse studentDeveloperCourse = studentDeveloperCourseMapper.transformToEntity(studentDeveloperCourseDTO);
        Integer studentId = studentDeveloperCourseDTO.getStudentId();
        Integer courseId = studentDeveloperCourseDTO.getDeveloperCourseId();
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<DeveloperCourse> developerCourse = developerCourseRepository.findById(courseId);
        if (!student.isPresent()) {
//            throw new IllegalArgumentException
//                ("Student with the following id = " + studentId + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!developerCourse.isPresent()) {
//            throw new IllegalArgumentException
//                ("Course with the following id = " + courseId + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (studentDeveloperCourseRepository.findByDeveloperCourseIdAndStudentId(courseId, studentId).isPresent()) {
//            throw new IllegalArgumentException
//                ("Student course combination is already present.");
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }
        return studentDeveloperCourseMapper.transformToDTO(studentDeveloperCourseRepository.save(studentDeveloperCourse));
    }

    @Override
    public void remove(Integer id) {
        Optional<StudentDeveloperCourse> studentDeveloperCourse = studentDeveloperCourseRepository.findById(id);
        if (!studentDeveloperCourse.isPresent()) {
//            throw new IllegalArgumentException
//                ("Student course with the following id = " + id + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        studentDeveloperCourseRepository.deleteById(id);
    }

    @Override
    public StudentDeveloperCourseDTO update(Integer id, StudentDeveloperCourseDTO studentDeveloperCourseDTO) {
        Integer studentId = studentDeveloperCourseDTO.getStudentId();
        Integer courseId = studentDeveloperCourseDTO.getDeveloperCourseId();
        Optional<StudentDeveloperCourse> oldStudentDeveloperCourse = studentDeveloperCourseRepository.findById(id);
        if (!oldStudentDeveloperCourse.isPresent()) {
//            throw new IllegalArgumentException
//                ("Student course with the following id = " + id + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        oldStudentDeveloperCourse.get().setDeveloperCourseId(studentDeveloperCourseDTO.getDeveloperCourseId());
        oldStudentDeveloperCourse.get().setStudentId(studentDeveloperCourseDTO.getStudentId());
        oldStudentDeveloperCourse.get().setClassesBought(studentDeveloperCourseDTO.getClassesBought());
        studentDeveloperCourseRepository.save(oldStudentDeveloperCourse.get());
        return studentDeveloperCourseMapper.transformToDTO(oldStudentDeveloperCourse.get());
    }

    @Override
    public List<StudentDeveloperCourseDTO> findByStudentId(Integer studentId) {
        Optional<List<StudentDeveloperCourse>> studentDeveloperCourse = studentDeveloperCourseRepository.findByStudentId(studentId);
        if (!studentDeveloperCourse.isPresent()) {
//            throw new IllegalArgumentException
//                ("Student with the following id = " + studentId + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return studentDeveloperCourseMapper.transformToListOfDTO(studentDeveloperCourse.get());
    }

    @Override
    public List<StudentDeveloperCourseDTO> findByDeveloperCourseId(Integer developerCourseId) {
        Optional<List<StudentDeveloperCourse>> studentDeveloperCourse = studentDeveloperCourseRepository.findByDeveloperCourseId(developerCourseId);
        if (!studentDeveloperCourse.isPresent()) {
//            throw new IllegalArgumentException
//                ("Course with the following id = " + developerCourseId + " is not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return studentDeveloperCourseMapper.transformToListOfDTO(studentDeveloperCourse.get());
    }

    @Override
    public StudentDeveloperCourseDTO findByDeveloperCourseIdAndStudentId(Integer developerCourseId, Integer studentId) {
        Optional<StudentDeveloperCourse> studentCourse = studentDeveloperCourseRepository.findByDeveloperCourseIdAndStudentId(developerCourseId, studentId);
        if (!studentCourse.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return studentDeveloperCourseMapper.transformToDTO(studentCourse.get());
    }
}
