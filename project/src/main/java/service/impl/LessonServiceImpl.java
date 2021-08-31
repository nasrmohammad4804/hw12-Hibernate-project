package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.Lesson;
import reository.impl.LessonRepositoryImpl;
import service.LessonService;

public class LessonServiceImpl extends BaseServiceImpl<Lesson, Long, LessonRepositoryImpl>
        implements LessonService {

    public LessonServiceImpl(LessonRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public void defaultLessonInTerm() {

        entityManager.getTransaction().begin();
        try {
            Lesson lesson1 = new Lesson("fizik1", 3);
            Lesson lesson2 = new Lesson("riazi1", 3);
            Lesson lesson3 = new Lesson("tarbiat badani", 1);
            Lesson lesson4 = new Lesson("andishe eslami", 2);
            Lesson lesson5 = new Lesson("c++", 3);
            Lesson lesson6 = new Lesson("java", 3);
            Lesson lesson7 = new Lesson("system amel", 3);
            Lesson lesson8 = new Lesson("medar manteghi", 3);
            Lesson lesson9 = new Lesson("zaban omoomi", 2);

            entityManager.persist(lesson1);
            entityManager.persist(lesson2);
            entityManager.persist(lesson3);
            entityManager.persist(lesson4);
            entityManager.persist(lesson5);
            entityManager.persist(lesson6);
            entityManager.persist(lesson7);
            entityManager.persist(lesson8);
            entityManager.persist(lesson9);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

    }
}
