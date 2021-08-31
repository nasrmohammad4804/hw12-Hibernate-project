package domain;

import base.domain.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue(value = "student")
@NamedQuery(name = "getReportCardForInputGradeByTeacher",query = "select average from Grade as g join g.results as average " +
        " join average.teacherLesson as at where at.lesson.id=:lessonId and " +
        " at.teacher.id=:teacherId and g.student=:myStudent")

@NamedQuery(name = "getGrades",query = "select  gr from Grade as g join g.results as gr where g.student.id=:myId")

@Table(uniqueConstraints = @UniqueConstraint(name = "unique_definition", columnNames = {Student.STUDENT_CODE}))
public class Student extends User {

    public static final String STUDENT_CODE="student_code";
    private static final String NAME_OF_TEMP_TABLE="student_addresses";


    @Column(name = STUDENT_CODE)
    private Long studentCode;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = NAME_OF_TEMP_TABLE)
    private List<Address> addresses;

    private String password;

    public Student() {
    }

    public Student(Long studentCode,List<Address> addresses) {
        this.studentCode = studentCode;

        this.addresses = addresses;
    }

    public Student(String firsName, String lastName,String password, LocalDate birthDay, Long studentCode) {
        super(firsName, lastName, birthDay);
        this.studentCode = studentCode;
        this.password=password;
    }

    public Long getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(Long studentCode) {
        this.studentCode = studentCode;
    }



    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    @Override
    public String toString() {
        return "Student{" +
                "firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", studentCode=" + studentCode +
                ", password="+password+
                ",\n addresses=" + addresses +
                '}';
    }

}
