package reository;

import base.repository.BaseRepository;
import domain.Lesson;
import domain.Student;
import domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends BaseRepository<Teacher,Long> {

    Optional<Teacher> findByTeacherCode(Long teacherCode,String password);

    List<Student> getAllStudents(Teacher teacher, Lesson lesson);
}
