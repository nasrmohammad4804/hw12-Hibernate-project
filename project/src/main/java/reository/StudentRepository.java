package reository;

import base.repository.BaseRepository;
import domain.Grade;
import domain.Lesson;
import domain.Student;
import domain.Teacher;
import domain.embeddable.Average;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends BaseRepository<Student,Long> {

    Average getAverage(Student student, Teacher teacher, Lesson lesson);

    Optional<Student> findByStudentCode(Long studentCode);

    void showGrade(Student student);

    Long countAll();
}
