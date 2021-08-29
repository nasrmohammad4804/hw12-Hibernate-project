package base.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<E extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private E id;

    private boolean isDeleted;

    public E getId() {
        return id;
    }

    public void setId(E id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
