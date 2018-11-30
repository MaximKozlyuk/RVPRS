package PO61.Kozlyuk.wdad.learn.xml;

import java.io.Serializable;

abstract class Human implements Serializable {

    private String firstName;
    private String secondName;

    Human(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    String getFirstName () {
        return this.firstName;
    }

    String getSecondName () {
        return this.secondName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(firstName);
        s.append(" ").append(secondName);
        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof Human)) { return false; }
        Human o = (Human) obj;
        return o.firstName.equals(firstName) && o.secondName.equals(secondName);
    }
}
