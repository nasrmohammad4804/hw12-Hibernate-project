package reository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Grade;
import domain.Student;
import reository.GradeRepository;

import javax.persistence.EntityManager;

public class GradeRepositoryImpl extends BaseRepositoryImpl<Grade, Long>
        implements GradeRepository {

    public GradeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Grade> getEntityClass() {
        return Grade.class;
    }

    @Override
    public Grade getGradeOfStudent(Student student) {

        try {
            return getEntityManager().createNamedQuery("getGradeWithStudent", Grade.class)
                    .setParameter("myId", student.getId()).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
