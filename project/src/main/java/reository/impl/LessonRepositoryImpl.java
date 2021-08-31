package reository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Lesson;

import javax.persistence.EntityManager;

public class LessonRepositoryImpl  extends BaseRepositoryImpl<Lesson,Long> {

    public LessonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Lesson> getEntityClass() {
       return Lesson.class;
    }
}
