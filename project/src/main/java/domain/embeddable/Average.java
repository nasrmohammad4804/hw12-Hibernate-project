package domain.embeddable;

import domain.Grade;
import domain.TeacherLesson;
import domain.enumaration.StudentState;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Average {

    private static final String STUDENT_ID="student_id";

    @ManyToOne
    @JoinColumn(name = STUDENT_ID, referencedColumnName = STUDENT_ID)
    Grade grade;

    @ManyToOne
    @JoinColumns({@JoinColumn(name = TeacherLesson.TEACHER_ID, referencedColumnName = TeacherLesson.TEACHER_ID),
            @JoinColumn(name = TeacherLesson.LESSON_ID, referencedColumnName = TeacherLesson.LESSON_ID)})
    TeacherLesson teacherLesson;

    private int score;

    @Enumerated(EnumType.STRING)
    private StudentState state;

    public Average() {
    }

    public Average(Grade grade, TeacherLesson teacherLesson) {

        this.grade = grade;
        this.teacherLesson = teacherLesson;
    }


    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public TeacherLesson getTeacherLesson() {
        return teacherLesson;
    }

    public void setTeacherLesson(TeacherLesson teacherLesson) {
        this.teacherLesson = teacherLesson;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public StudentState getState() {
        return state;
    }

    public void setState(StudentState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Average average = (Average) o;
        return score == average.score && Objects.equals(grade, average.grade) && Objects.equals(teacherLesson, average.teacherLesson) && state == average.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, teacherLesson, score, state);
    }

    @Override
    public String toString() {
        return "Result{" +
                grade +
                ", teacher{" + "name :" + teacherLesson.getTeacher().getFirsName() +
                "    " + "family :" + teacherLesson.getTeacher().getLastName() + "}" +
                "    " + "lessonName :" + teacherLesson.getLesson().getName() +
                ", score=" + score +
                ", state=" + state +
                '}';
    }
}
