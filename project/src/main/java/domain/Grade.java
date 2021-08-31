package domain;

import base.domain.BaseEntity;
import domain.embeddable.Average;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@AttributeOverride(name = "id", column = @Column(name = Grade.PRIMARY_KEY_NAME))
@NamedQuery(name = "getGradeWithStudent",query = "select g from Grade as g where g.id=:myId")
public class Grade extends BaseEntity<Long> {


    public static final String PRIMARY_KEY_NAME = "student_id";

    @OneToOne
    @MapsId(value = PRIMARY_KEY_NAME)
    private Student student;

    @ElementCollection
//    @JoinTable( joinColumns = @JoinColumn(name = "id",referencedColumnName = PRIMARY_KEY_NAME))
    private List<Average> results = new ArrayList<>();

    public Grade() {
    }

    public Grade(Student student) {

        this.student = student;

    }

    public Grade(Student student, List<Average> results) {
        this(student);
        this.results = results;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Average> getResults() {
        return results;
    }

    public void setResults(List<Average> results) {
        this.results = results;
    }


    @Override
    public String toString() {
        return
                "{student=" + "name :" + student.getFirsName() + "    " + "family :" + student.getLastName() +
                        '}';
    }
}
