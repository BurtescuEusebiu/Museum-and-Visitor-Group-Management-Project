package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FindGuide implements Command {
    private Group group;
    private String path;
    private Professor professor;

    public FindGuide(Group group, String outPath, Professor professor) {
        this.group = group;
        this.path = outPath;
        this.professor = professor;
    }

    @Override
    public void execute() {
        if(group == null) {
            throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
        }
        try {
            String answer = null;
            if(group.findGuide(professor) == true) {
                answer = "guide found: ";
            }
            else answer = "guide not exists: ";
                BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
                writer.write(group.getMuseumCode() + " ## " + group.getTimetable() + " ## " + answer + professor.toString() + "\n");
                writer.close();
        }
        catch (IOException e) {};

    }
}
