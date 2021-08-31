package util;

import reository.impl.*;
import service.impl.*;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class ApplicationContext {

    private static ApplicationContext applicationContext;

    private final Scanner scannerForString = new Scanner(System.in);
    private final Scanner scannerForInteger = new Scanner(System.in);


    private final EntityManager entityManager = HibernateUtil.entityManagerFactory().createEntityManager();
    private final GradeServiceImpl gradeService; /*new GradeServiceImpl(new GradeRepositoryImpl(entityManager));*/
    private final LessonServiceImpl lessonService;/* new LessonServiceImpl(new LessonRepositoryImpl(entityManager));*/
    private final TeacherServiceImpl teacherService;/*new TeacherServiceImpl(new TeacherRepositoryImpl(entityManager));*/
    private final StudentServiceImpl studentService; /*new StudentServiceImpl(new StudentRepositoryImpl(entityManager));*/



    private final TeacherLessonServiceImpl teacherLessonService;

    private static Long studentCode = 994000L;


    private ApplicationContext() {

        gradeService = new GradeServiceImpl(new GradeRepositoryImpl(entityManager));
        lessonService = new LessonServiceImpl(new LessonRepositoryImpl(entityManager));
        teacherService = new TeacherServiceImpl(new TeacherRepositoryImpl(entityManager));
        studentService = new StudentServiceImpl(new StudentRepositoryImpl(entityManager));

        teacherLessonService = new TeacherLessonServiceImpl(new TeacherLessonRepositoryImpl(entityManager));

    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null)
            synchronized (ApplicationContext.class) {

                applicationContext = new ApplicationContext();
            }

        return applicationContext;
    }

    public StudentServiceImpl getStudentService() {
        return studentService;
    }

    public TeacherServiceImpl getTeacherService() {
        return teacherService;
    }

    public LessonServiceImpl getLessonService() {
        return lessonService;
    }

    public TeacherLessonServiceImpl getTeacherLessonService() {
        return teacherLessonService;
    }

    public GradeServiceImpl getGradeService() {
        return gradeService;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Scanner getScannerForString() {
        return scannerForString;
    }

    public Scanner getScannerForInteger() {
        return scannerForInteger;
    }

    public Long getStudentCode() {


        return studentCode+studentService.countAll();
    }
}
