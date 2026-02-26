package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {
    private List<Person> members = new ArrayList<>();
    private Professor guide;
    private Integer museumCode;
    private String timetable;

    public Group(Integer museumCode, String timetable) {
        this.museumCode = museumCode;
        this.timetable = timetable;
    }

    public Integer getMuseumCode() {
        return museumCode;
    }

    public String getTimetable() {
        return timetable;
    }

    public Professor getGuide() {
        return guide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return museumCode == group.museumCode && timetable.equals(group.timetable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(museumCode, timetable);
    }


    public void addGuide(Professor guide){

        if(guide.getRole().equals("ghid"))
        {if(this.guide!=null)
                throw new GuideExistsException("GuideExistsException: Guide already exists.");
                this.guide = (Professor) guide;}
        else
            throw new GuideTypeException("GuideTypeException: Guide must be a professor.");
    }

    public boolean findGuide(Professor guide){
        if (this.guide.equals(guide))
            return true;
        return false;
    }

    public void addMember(Person member){
        if(members.size() == 10)
            throw new GroupThresholdException("GroupThresholdException: Group cannot have more than 10 members.");
        this.members.add(member);
    }

    public boolean findMember(Person member){
        if(members.contains(member))
            return true;
        return false;
    }

    public void removeMember(Person member){
        if(!findMember(member)) throw new PersonNotExistsException("PersonNotExistsException: Person was not found in the group.");
        try {this.members.remove(member);}
        catch(Exception e){
            throw new PersonNotExistsException("PersonNotExistsException: Person was not found in the group.");
        }
    }

    public void resetGuide(){
        this.guide = null;
    }


}
