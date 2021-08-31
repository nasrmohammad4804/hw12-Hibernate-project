package service;

import base.service.BaseService;
import domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService extends BaseService<Student,Long> {

     Student register();

     Optional<Student> login();

     void showGrade(Student student);

     void chooseLessons(Student student);

     void showAllStudentOfTeacher(List<Student> students);

     Long countAll();
}
