package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.TeacherLesson;
import reository.impl.TeacherLessonRepositoryImpl;
import service.TeacherLessonService;

public class TeacherLessonServiceImpl extends BaseServiceImpl<TeacherLesson,Long,
        TeacherLessonRepositoryImpl> implements TeacherLessonService {

    public TeacherLessonServiceImpl(TeacherLessonRepositoryImpl repository) {
        super(repository);
    }


}
