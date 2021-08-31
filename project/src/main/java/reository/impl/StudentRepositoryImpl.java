package reository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Grade;
import domain.Lesson;
import domain.Student;
import domain.Teacher;
import domain.embeddable.Average;
import reository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student, Long>
        implements StudentRepository {

    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {

        return Student.class;
    }

    @Override
    public Average getAverage(Student student, Teacher teacher, Lesson lesson) {

        return getEntityManager().createNamedQuery("getReportCardForInputGradeByTeacher", Average.class)
                .setParameter("lessonId", lesson.getId()).setParameter("teacherId", teacher.getId())
                .setParameter("myStudent", student).getSingleResult();
    }


    @Override
    public Optional<Student> findByStudentCode(Long studentCode) {

        Optional<Student> optionalStudent = Optional.empty();
        try {

            Student student = getEntityManager().createQuery("select s from Student as s " +
                    "where s.studentCode=:code", Student.class)
                    .setParameter("code", studentCode).getSingleResult();

            optionalStudent = Optional.of(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionalStudent;
    }

    @Override
    public void showGrade(Student student) {


        getEntityManager().createNamedQuery("getGrades", Average.class)
                .setParameter("myId", student.getId())
                .getResultList().forEach(System.out::println);

    }

    @Override
    public Long countAll() {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);

        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.select(builder.count(root.get("id")));

        try {

            return getEntityManager().createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
