package org.example;

import java.util.HashSet;
import java.util.Set;

public class Database {
    private static Database database;
    private Set<Museum> museums = new HashSet<>();
    private Set<Group> groups = new HashSet<>();

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void resetDatabase() {
        museums.clear();
        groups.clear();
    }

    public Set<Museum> getMuseums() {
        return museums;
    }

    public Museum getMuseum(Integer code) {
        return museums.stream().filter(m -> m.getCode() == code).findFirst().orElse(null);
    }

    public void addMuseum(Museum museum) {
        museums.add(museum);
    }

    public void addMuseums(Set<Museum> museums) {
        for (Museum museum : museums) {
            addMuseum(museum);
        }
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public Group getMainGroup(Integer code, String timetable){
        Group group = groups.stream()
                .filter(g -> g.getMuseumCode().equals(code) && g.getTimetable().equals(timetable))
                .findFirst()
                .orElse(null);

        if (group == null) {
            group = new Group(code, timetable);
            groups.add(group);
        }

        return group;
    }

    public Group groupExists(Integer code, String timetable){
        Group group = groups.stream()
                .filter(g -> g.getMuseumCode().equals(code) && g.getTimetable().equals(timetable))
                .findFirst()
                .orElse(null);
        return group;
    }

    public void addGroups(Set<Group> groups) {
        for (Group group : groups) {
            addGroup(group);
        }
    }




}
