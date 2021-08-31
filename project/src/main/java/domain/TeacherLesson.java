package domain;

import base.domain.BaseEntity;
import domain.embeddable.TeacherLessonId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({@NamedQuery(name = "getAllTeacherPresentLesson",
        query = "select tl from Grade as g join g.results as av join av.teacherLesson as tl " +
                "where tl.id=:myId")})
public class TeacherLesson extends BaseEntity<Long> implements Serializable {

    public static final String LESSON_ID = "lesson_id";
    public static final String TEACHER_ID = "teacher_id";


    @Embedded
    private TeacherLessonId arr;


    @ManyToOne
    @MapsId(value = LESSON_ID)
    private Lesson lesson;

    @ManyToOne
    @MapsId(value = TEACHER_ID)
    private Teacher teacher;


    private int capacity;

    public TeacherLesson() {
    }

    public TeacherLesson(Lesson lesson, Teacher teacher, int capacity) {
        this.lesson = lesson;
        this.teacher = teacher;
        this.capacity = capacity;
    }

    public TeacherLesson(Lesson lesson, Teacher teacher,TeacherLessonId arr) {
        this.lesson = lesson;
        this.teacher = teacher;
        this.arr =new TeacherLessonId();
    }

    public TeacherLesson(TeacherLessonId arr, Lesson lesson, Teacher teacher, int capacity) {
        this(lesson, teacher, capacity);
        this.arr = arr;

    }

    public TeacherLessonId getArr() {
        return arr;
    }
    public void setArr(TeacherLessonId id) {
        this.arr = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "TeacherLesson{" +
                "arr=" + arr +
                ", lesson=" + lesson +
                ", teacher=" + teacher +
                ", capacity=" + capacity +
                '}';
    }
}
