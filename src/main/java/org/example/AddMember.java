package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddMember implements Command {
    private Group group;
    private Person member;
    private String path;

    public AddMember(Group group, Person member, String outPath) {
        this.group = group;
        this.member = member;
        this.path = outPath;
    }

    @Override
    public void execute() {
        if(group == null) throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
        group.addMember(member);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(group.getMuseumCode() + " ## " +  group.getTimetable() + " ## " + "new member: " + member.toString() + "\n");
            writer.close();
        }
        catch (IOException e) {};
    }
}
