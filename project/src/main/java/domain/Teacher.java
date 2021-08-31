package domain;

import base.domain.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "unique_definition",
        columnNames = {Teacher.TEACHER_CODE})})
@DiscriminatorValue(value = "teacher")

public class Teacher extends User {


    public static final String TEACHER_CODE = "teacher_code";
    public static final String TEACHER_ID = "tec_id";

    private static final String FOREIGN_KEY_NAME_IN_ADDRESS = "address_id";


    @AttributeOverride(name = "id", column = @Column(name = TEACHER_ID))

    @Column(name = TEACHER_CODE)
    private long teacherCode;

    @Column(nullable = false)
    private double salary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<TeacherLesson> teacherLessons = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = FOREIGN_KEY_NAME_IN_ADDRESS)
    private Address address;

    private String password;

    public Teacher() {
    }

    public Teacher(long teacherCode, double salary, Address address) {
        this.teacherCode = teacherCode;
        this.salary = salary;
        this.address = address;
    }

    public Teacher(String firsName, String lastName, LocalDate birthDay) {
        super(firsName, lastName, birthDay);
    }

    public Teacher(String firsName, String lastName, String password, LocalDate birthDay, long teacherCode, double salary) {
        this(firsName, lastName, birthDay);
        this.password = password;
        this.teacherCode = teacherCode;
        this.salary = salary;
    }

    public Teacher(String firsName, String lastName, LocalDate birthDay, List<TeacherLesson> teacherLessons) {
        super(firsName, lastName, birthDay);
        this.teacherLessons = teacherLessons;
    }

    public long getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(long teacherCode) {
        this.teacherCode = teacherCode;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<TeacherLesson> getTeacherLessons() {
        return teacherLessons;
    }

    public void setTeacherLessons(List<TeacherLesson> teacherLessons) {
        this.teacherLessons = teacherLessons;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                ", teacherCode=" + teacherCode +
                ", password=" + password +
                ", salary=" + salary +

                '}';
    }

    public String printInformationWhenStudentSelectUnit() {

        return "Teacher{" +
                "firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'';
    }


}
