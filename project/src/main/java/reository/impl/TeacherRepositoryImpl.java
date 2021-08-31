package reository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Lesson;
import domain.Student;
import domain.Teacher;
import reository.TeacherRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TeacherRepositoryImpl extends BaseRepositoryImpl<Teacher, Long>
        implements TeacherRepository {

    public TeacherRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }

    @Override
    public Optional<Teacher> findByTeacherCode(Long teacherCode, String password) {

        Optional<Teacher> optional = Optional.empty();

        try {

            Teacher teacher = getEntityManager().createQuery("select t from Teacher as t where " +
                    "t.teacherCode=:code and t.password=:myPassword", Teacher.class)
                    .setParameter("code", teacherCode).setParameter("myPassword", password).getSingleResult();

            optional = Optional.of(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optional;
    }

    @Override
    public List<Student> getAllStudents(Teacher teacher, Lesson lesson) {

        return getEntityManager().createQuery("select g.student from  Grade as g join g.results as a " +
                " where a.teacherLesson.lesson.id=:lessId and a.teacherLesson.teacher.id=:teaId", Student.class)
                .setParameter("lessId", lesson.getId()).setParameter("teaId", teacher.getId())
                .getResultList();
    }
}
