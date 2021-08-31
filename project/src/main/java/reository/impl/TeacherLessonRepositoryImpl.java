package reository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.TeacherLesson;
import reository.TeacherLessonRepository;

import javax.persistence.EntityManager;

public class TeacherLessonRepositoryImpl extends BaseRepositoryImpl<TeacherLesson,Long>
 implements TeacherLessonRepository {

    public TeacherLessonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<TeacherLesson> getEntityClass() {
        return TeacherLesson.class;

    }

}
