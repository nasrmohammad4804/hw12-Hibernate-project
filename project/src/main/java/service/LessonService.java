package service;

import base.service.BaseService;
import domain.Lesson;

public interface LessonService extends BaseService<Lesson, Long> {

    void defaultLessonInTerm();


}
