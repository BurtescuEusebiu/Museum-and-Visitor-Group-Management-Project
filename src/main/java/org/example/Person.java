package org.example;

import java.util.Objects;

public abstract class Person {
    protected String surname;
    protected String name;
    protected String role;
    protected int age;
    protected String email;

    public Person(String surname, String name, String role) {
        this.surname = surname;
        this.name = name;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person member = (Person) o;
        return surname.equals(member.surname) && name.equals(member.name) && role.equals(member.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, role);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole(){
        return role;
    }

    @Override
    public String toString() {
        return "surname=" + surname + ", name=" + name + ", role=" + role + ", age=" + age + ", email=" + email + ",";
    }
}
