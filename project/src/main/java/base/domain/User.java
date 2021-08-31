package base.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class User extends BaseEntity<Long> {

    public static final String FIRST_NAME = "first_name";

    public static final String LAST_NAME = "last_name";


    @Column(name = FIRST_NAME)
    protected String firsName;

    @Column(name = LAST_NAME)
    protected String lastName;

    protected LocalDate birthDay;


    public User() {
    }

    public User(String firsName, String lastName, LocalDate birthDay) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.birthDay = birthDay;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
