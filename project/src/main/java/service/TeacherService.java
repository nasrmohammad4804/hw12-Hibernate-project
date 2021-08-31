package service;

import base.service.BaseService;
import domain.Teacher;

import java.util.Optional;

public interface TeacherService extends BaseService<Teacher,Long> {

    Optional<Teacher> login();

    void confirmStudentGrade(Teacher teacher);

    void addTeacherAlreadyExists();

    void presentOfLesson(Teacher teacher);
}
