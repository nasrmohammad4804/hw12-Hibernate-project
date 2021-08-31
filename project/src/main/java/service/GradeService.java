package service;

import base.service.BaseService;
import domain.Grade;
import domain.Student;

public interface GradeService  extends BaseService<Grade,Long> {
    Grade getGradeOfStudent(Student student);
}
