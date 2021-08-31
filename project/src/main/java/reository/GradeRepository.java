package reository;

import base.repository.BaseRepository;
import domain.Grade;
import domain.Student;

public interface GradeRepository extends BaseRepository<Grade,Long> {

    Grade getGradeOfStudent(Student student);
}
