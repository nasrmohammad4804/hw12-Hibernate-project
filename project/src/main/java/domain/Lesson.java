package domain;

import base.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "unique_definition", columnNames = {"name"}))
public class Lesson extends BaseEntity<Long> {

    private String name;
    private int unit;

//    public static final String NAME_OF_ID_IN_LESSON = "less_id";

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private List<TeacherLesson> teacherLessons = new ArrayList<>();

    public Lesson() {
    }

    public Lesson(String name, int unit) {
        this.name = name;
        this.unit = unit;
    }

    public Lesson(String name, int unit, List<TeacherLesson> teacherLessons) {
        this(name, unit);
        this.teacherLessons = teacherLessons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public List<TeacherLesson> getTeacherLessons() {
        return teacherLessons;
    }

    public void setTeacherLessons(List<TeacherLesson> teacherLessons) {
        this.teacherLessons = teacherLessons;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                ", id=" + getId() +
                ", name='" + name + '\'' +
                ", unit=" + unit +

                '}';
    }
}
