package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RemoveGuide implements Command {
    private Group group;
    private String path;
    private Museum museum;

    public RemoveGuide(Museum musem, Group group, String outPath) {
        this.group = group;
        this.path = outPath;
        this.museum = musem;
    }

    @Override
    public void execute() {
        if(group == null) {
            throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(group.getMuseumCode() + " ## " + group.getTimetable() + " ## " + "removed guide: " + group.getGuide().toString() + "\n");
            writer.close();
            if(museum != null)museum.removeGuide(group.getGuide().getEmail());
            group.resetGuide();


        }
        catch (IOException e) {};

    }
}
