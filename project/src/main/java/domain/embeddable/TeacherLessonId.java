package domain.embeddable;

import domain.TeacherLesson;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeacherLessonId implements Serializable {

    @Column(name = TeacherLesson.TEACHER_ID)
    private Long TeacherId;

    @Column(name = TeacherLesson.LESSON_ID)
    private Long LessonId;

    public TeacherLessonId() {
    }

    public TeacherLessonId(Long teacherId, Long lessonId) {
        TeacherId = teacherId;
        LessonId = lessonId;
    }

    public Long getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(Long teacherId) {
        TeacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherLessonId that = (TeacherLessonId) o;
        return Objects.equals(TeacherId, that.TeacherId) && Objects.equals(LessonId, that.LessonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TeacherId, LessonId);
    }

    public Long getLessonId() {
        return LessonId;
    }

    public void setLessonId(Long lessonId) {
        LessonId = lessonId;
    }
}
