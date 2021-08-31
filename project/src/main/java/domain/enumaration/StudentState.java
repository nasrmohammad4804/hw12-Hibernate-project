package domain.enumaration;

public enum StudentState {

    PASSED,FAIL;

    @Override
    public String toString() {
        return this.name();
    }
}
