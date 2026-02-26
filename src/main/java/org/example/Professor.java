package org.example;

public class Professor extends Person {
    private int experience;
    private String school;

    public Professor(String surname, String name, String role) {
        super(surname, name, role);
    }

    @Override
    public String getRole(){
        return role;
    }

    @Override
    public String toString() {
        return super.toString() + " school=" + school + ", experience=" + experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
