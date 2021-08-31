package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.Grade;
import domain.Student;
import reository.impl.GradeRepositoryImpl;
import service.GradeService;

public class GradeServiceImpl extends BaseServiceImpl<Grade,Long, GradeRepositoryImpl>
 implements GradeService {

    public GradeServiceImpl(GradeRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public Grade getGradeOfStudent(Student student) {
        return repository.getGradeOfStudent(student);
    }
}
